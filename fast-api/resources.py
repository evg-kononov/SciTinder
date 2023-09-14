import torch
import pickle
import pandas as pd


def load_adjacency_list(path: str):
    with open(path, "rb") as f:
        adjacency_list = pickle.load(f)
        return adjacency_list


def load_publication_author(path: str):
    df = pd.read_parquet(path)
    return df


def load_corpus_embeddings(path: str):
    corpus_embeddings = torch.load(path)
    norm = torch.norm(corpus_embeddings, dim=1)
    if norm.sum() != corpus_embeddings.shape[0]:
        corpus_embeddings = corpus_embeddings / torch.unsqueeze(norm, 1)
    return corpus_embeddings


def load_embedding_idxs(path: str):
    embedding_idxs = torch.load(path)
    return embedding_idxs