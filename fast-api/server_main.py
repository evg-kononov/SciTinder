import json
import redis
import uuid

import uvicorn

import pandas as pd
import numpy as np
from contextlib import asynccontextmanager
from typing import Union, Annotated, List, Set

import requests
import torch
from fastapi import FastAPI, Body, UploadFile, File, HTTPException, Form
from pydantic import BaseModel, Field
from util import dot_score, find_by_id
from resources import *
from config import *
from entourage_search import author_preparation, async_get_data_from_list
from visualization import graph_visualization, graph_data_preparation, create_edges, create_nodes

r = redis.Redis(host=REDIS_HOST, port=REDIS_PORT, decode_responses=True)

cache_data = {}
cache_data["adjacency_list"] = load_adjacency_list(adjacency_list_path)
cache_data["publication_author"] = load_publication_author(publication_author_path)
cache_data["corpus_embeddings"] = load_corpus_embeddings(corpus_embeddings_path)
cache_data["embedding_idxs"] = load_embedding_idxs(embedding_idxs_path)


@asynccontextmanager
async def lifespan(app: FastAPI):
    # TODO: Почему-то не работает, возможно, ассинхронная загрузка файлов работает так себе (словарь пустой потом)
    #cache_data["adjacency_list"] = load_adjacency_list(adjacency_list_path)
    #cache_data["publication_author"] = load_publication_author(publication_author_path)
    #cache_data["corpus_embeddings"] = load_corpus_embeddings(corpus_embeddings_path)
    #cache_data["embedding_idxs"] = load_embedding_idxs(embedding_idxs_path)
    yield
    # Clean up the resources
    cache_data.clear()


app = FastAPI(lifespan=lifespan)


class Filter(BaseModel):
    organizations: Union[Set[str], None] = set()
    min_h_index: Annotated[Union[int, None], Field(ge=0)] = None
    max_h_index: Annotated[Union[int, None], Field(ge=0)] = None


class SimilarityResponse(BaseModel):
    id: int
    name: str
    h_index: Union[int, None] = None
    scopus_id: Union[int, None] = None
    organization: Union[dict, None] = None
    similarity: float


def apply_filter(filter: Filter):
    pass


def sbert_predict(queries: List[str]):
    response = requests.post(
        TORCH_SERVE_PREDICT_URL,
        data={"data": json.dumps({"queries": queries})}
    )

    if response.status_code == 200:
        query_embeddings = response.json()
        query_embeddings = torch.mean(torch.Tensor(query_embeddings), dim=0, keepdims=True)
        return query_embeddings
    else:
        raise HTTPException(status_code=response.status_code, detail="Sbert prediction failed")


def calculate_similarity(query_embedding, corpus_embeddings, embedding_idxs, top_k):
    scores = dot_score(query_embedding, corpus_embeddings)[0]
    top_results = torch.topk(scores, k=top_k)
    target_idxs = embedding_idxs[top_results.indices]
    similarity = top_results.values.numpy()
    return similarity, target_idxs


@app.post("/similarity-search/findById/")
def similarity_search( # async def or just "def"???
        source_id: Annotated[int, Body(title="Source ID", description="Scientist ID from the database (primary key)")],
        target_filter: Annotated[Union[Filter, None], Body()] = None,
        top_k: Annotated[Union[int, None], Body(gt=0)] = 10,
        exclude_coauthors: Annotated[Union[bool, None], Body()] = True
) -> list[SimilarityResponse]:
    corpus_embeddings = cache_data["corpus_embeddings"]
    embedding_idxs = cache_data["embedding_idxs"]
    if len(corpus_embeddings) != len(embedding_idxs):
        raise HTTPException(
            status_code=418,
            detail="The length of 'corpus_embeddings' must be the same as the length of 'embedding_idxs'"
        )

    idx = list(embedding_idxs).index(source_id)
    query_embedding = corpus_embeddings[idx:idx + 1]

    if exclude_coauthors:
        coauthors = cache_data["adjacency_list"][source_id] + [source_id]
        required_idxs = np.isin(embedding_idxs, coauthors, invert=True)
        corpus_embeddings = corpus_embeddings[required_idxs]
        embedding_idxs = embedding_idxs[required_idxs]

    if target_filter:
        required_idxs = apply_filter(filter=target_filter)

    similarity, target_idxs = calculate_similarity(query_embedding, corpus_embeddings, embedding_idxs, top_k)

    # TODO: здесь добавляем остальную метаинформацию о ученых и возвращаем вместе с similarity
    idxs_sim_dict = dict(zip(target_idxs, similarity))
    response = find_by_id(target_idxs, GET_AUTHOR_BY_ID)
    for i, author in enumerate(response):
        author["similarity"] = idxs_sim_dict[author["id"]]
        response[i] = SimilarityResponse(**author)

    # TODO: сделать сортировку response по similarity
    # response = SimilarityResponse(similarity=similarity)
    return response


@app.post("/similarity-search/findByPrompt/") # or app.put or app.post??? because "get" doesn't have a "request body"
def similarity_search( # async def or just "def"???
        prompt: Annotated[List[str], Body(title="Prompt", description="A text(s) used to search for closest scientists")],
        target_filter: Annotated[Union[Filter, None], Body()] = None,
        top_k: Annotated[Union[int, None], Body(gt=0)] = 10,
) -> list[SimilarityResponse]:
    corpus_embeddings = cache_data["corpus_embeddings"]
    embedding_idxs = cache_data["embedding_idxs"]
    if len(corpus_embeddings) != len(embedding_idxs):
        raise HTTPException(
            status_code=418,
            detail="The length of 'corpus_embeddings' must be the same as the length of 'embedding_idxs'"
        )

    # TODO: можно сделать функцию apply_target_filter и поставить её до подсчета score (и так во всех запросах)
    if target_filter:
        required_idxs = apply_filter(filter=target_filter)

    query_embedding = sbert_predict(prompt)
    similarity, target_idxs = calculate_similarity(query_embedding, corpus_embeddings, embedding_idxs, top_k)

    # TODO: здесь добавляем остальную метаинформацию о ученых и возвращаем вместе с similarity
    idxs_sim_dict = dict(zip(target_idxs, similarity))
    authors = find_by_id(target_idxs, GET_AUTHOR_BY_ID)
    for author in authors:
        author["similarity"] = idxs_sim_dict[author["id"]]

    # TODO: сделать нормальный response черезе Similarity, чтобы свагер мог это пропарсить и сказать пользователю, что возвращает API
    # response = SimilarityResponse(similarity=similarity)
    response = {"authors": authors}
    return response


@app.post("/caching/filter/")
def filter_caching(
        target_filter: Annotated[Union[Filter, None], Body()],
) -> str:
    target_uuid = str(uuid.uuid4())
    r.set(target_uuid, target_filter.model_dump_json())
    return target_uuid


@app.post("/similarity-search/findByFile/")
def similarity_search(
        files: Annotated[
            List[UploadFile],
            File(title="File", description="A file that contains several prompts to search for closest scientists")
        ],
        target_uuid: Annotated[
            Union[str, None], Form(title="UUID", description="UUID of filter that have been saved in redis")
        ] = None,
        top_k: Annotated[Union[int, None], Form(gt=0)] = 10,
) -> list[SimilarityResponse]:
    target_filter = None
    if target_uuid:
        target_filter = json.loads(r.get(target_uuid))

    # TODO: понять, как работать с файлом и с каким файлом можно работать? Нужно как-то удалить сразу здесь ПУСТЫЕ СТРОКИ
    if files[0].filename.split(".")[-1] == "txt":
        contents = pd.read_csv(files[0].file, header=None)
        prompt = contents[0].to_list()
        #await files[0].close()


    corpus_embeddings = cache_data["corpus_embeddings"]
    embedding_idxs = cache_data["embedding_idxs"]
    if len(corpus_embeddings) != len(embedding_idxs):
        raise HTTPException(
            status_code=418,
            detail="The length of 'corpus_embeddings' must be the same as the length of 'embedding_idxs'"
        )

    if target_filter:
        required_idxs = apply_filter(filter=target_filter)

    query_embedding = sbert_predict(prompt)
    similarity, target_idxs = calculate_similarity(query_embedding, corpus_embeddings, embedding_idxs, top_k)

    # TODO: здесь добавляем остальную метаинформацию о ученых и возвращаем вместе с similarity
    idxs_sim_dict = dict(zip(target_idxs, similarity))
    authors = find_by_id(target_idxs, GET_AUTHOR_BY_ID)
    for author in authors:
        author["similarity"] = idxs_sim_dict[author["id"]]

    # TODO: сделать нормальный response черезе Similarity, чтобы свагер мог это пропарсить и сказать пользователю, что возвращает API
    # response = SimilarityResponse(similarity=similarity)
    response = {"authors": authors}
    return response


@app.post("/plotly/create-figure/")
def create_figure(
        source_id: Annotated[int, Body(title="Source ID", description="Scientist ID from the database (primary key)")],
        target_id: Annotated[int, Body(title="Target ID", description="Scientist ID from the database (primary key)")],
        knees_num: Annotated[Union[int, None], Body(gt=0)] = 10,
):
        pos, node_idxs = graph_data_preparation(cache_data["adjacency_list"], source_id, target_id, knees_num=knees_num)
        nodes = create_nodes(node_idxs, GET_AUTHOR_BY_ID)
        edges = create_edges(node_idxs, cache_data["publication_author"])
        fig = graph_visualization(nodes, edges, pos)
        return fig.to_json()


if __name__ == "__main__":
    uvicorn.run(app, host="127.0.0.1", port=8009)
