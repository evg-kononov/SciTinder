import requests
import copy
import asyncio
import aiohttp
import pickle


def flatten(l: list):
    return [item for sublist in l for item in sublist]


def get_data(url: str, params: dict = None):
    response = requests.get(url, params)
    if response.status_code == 200:
        return response.json()
    else:
        print(f"There's a {response.status_code} error with your request!")


def author_preparation(author: dict):
    author = copy.deepcopy(author)
    if author["organization"] is not None:
        author["organization_name"] = author.get("organization").get("name")
    else:
        author["organization_name"] = ""
    author.pop("organization")
    author.pop("scopus_id")
    return author


def dict_intersection(a: dict, b: dict):
    inter = list(
        set(flatten([values for key, values in a.items()])) & set(flatten([values for key, values in b.items()]))
    )
    return inter


async def async_get_data(url: str, session):
    async with session.get(url) as response:
        return await response.json()


async def async_get_data_from_list(urls: list):
    async with aiohttp.ClientSession() as session:
        tasks = []
        for url in urls:
            task = asyncio.create_task(async_get_data(url, session))
            tasks.append(task)
        data = await asyncio.gather(*tasks)
        return data


def find_tree_for_stats(adjacency_list: dict, source_id: int, knees_num: int):
    source_entourages = {}
    source_family_tree = {}

    source_family_tree[source_id] = adjacency_list[source_id]
    source_entourages[1] = adjacency_list[source_id]
    for knee in range(knees_num - 1):
        source_family_tree = {
            value: adjacency_list[value] for key, values in source_family_tree.items() for value in values
        }
        source_entourages[knee + 1] = source_family_tree
    return list(map(lambda x: len(source_entourages[x]), source_entourages.keys()))


def find_tree_from_list_eugene(adjacency_list: dict, source_id: int, target_id: int, knees_num: int):
    inter_list = []
    inter_list.append([source_id, target_id])

    source_family_tree = {}
    target_family_tree = {}

    source_entourages = {}
    target_entourages = {}

    source_family_tree[source_id] = adjacency_list[source_id]
    target_family_tree[target_id] = adjacency_list[target_id]

    source_entourages[1] = source_family_tree
    target_entourages[1] = target_family_tree

    if len(source_family_tree[source_id]) == 0 and len(target_family_tree[target_id]) == 0:
        return {"source_entourages": source_entourages, "target_entourages": target_entourages}, None

    inter = dict_intersection(source_family_tree, target_family_tree)
    if len(inter) != 0:
        inter_list.append(inter)
        return {"source_entourages": source_entourages, "target_entourages": target_entourages}, list(set(flatten(inter_list)))

    for knee in range(1, 2 * knees_num):
        source_family_tree = {
            value: adjacency_list[value] for key, values in source_family_tree.items() for value in values
        }
        target_family_tree = {
            value: adjacency_list[value] for key, values in target_family_tree.items() for value in values
        }

        source_entourages[knee + 1] = source_family_tree
        target_entourages[knee + 1] = target_family_tree

        # Пересечение knee-окружения с первым окружением target
        inter = dict_intersection(source_family_tree, target_entourages[1])
        if len(inter) != 0:
            inter_list.append(inter)
            break

    # Если за заданное количество knee нет пересечения с первым окружением target -> известные окружения
    if len(inter) == 0:
        return {"source_entourages": source_entourages, "target_entourages": target_entourages}, None
    # Иначе двигаемся с обратном порядке: пересечение предпоследнего knee source со вторым target и т.д.
    else:
        start_knee = max(source_entourages.keys()) - 1
        # !!! Чтобы ускорить, можно в этом цикле считать остальные окружения для target, а не вместе с окружением source
        for knee in range(start_knee, 0, -1):
            inter = dict_intersection(source_entourages[knee], target_entourages[start_knee - knee + 2])
            inter_list.append(inter)

        return {"source_entourages": source_entourages, "target_entourages": target_entourages}, list(set(flatten(inter_list)))


def find_tree_from_list(adjacency_list: dict, source_id: int, target_id: int, knees_num: int):
    source_tree = {}
    target_tree = {}

    for i in range(knees_num):
        if i == 0:
            source_tree[i] = set(adjacency_list[source_id])
            target_tree[i] = set(adjacency_list[target_id])
        else:
            temp = set()
            for item in target_tree[i - 1]:
                temp.update(adjacency_list[item])
            target_tree[i] = temp

        inter = source_tree[i].intersection(target_tree[i])
        if len(inter) > 0:
            return source_tree, target_tree, inter
        else:
            temp = set()
            for item in source_tree[i]:
                temp.update(adjacency_list[item])
            source_tree[i + 1] = temp

            inter = source_tree[i + 1].intersection(target_tree[i])
            if len(inter) > 0:
                return source_tree, target_tree, inter

    return source_tree, target_tree, None


def unpack(
        adjacency_list: dict,
        source_tree: dict,
        target_tree: dict,
        inter: set,
        source_id: int,
        target_id: int
):
    source_tree[len(source_tree) - 1] = inter
    target_tree.pop(len(target_tree) - 1)

    for i in range(len(source_tree) - 2, -1, -1):
        source_tree[i] = set(
            item for item in source_tree[i] if len(set(adjacency_list[item]).intersection(source_tree[i + 1])) > 0
        )

    for i in range(len(target_tree) - 1, -1, -1):
        if i == len(target_tree) - 1:
            target_tree[i] = set(
                item for item in target_tree[i] if len(set(adjacency_list[item]).intersection(inter)) > 0
            )
        else:
            target_tree[i] = set(
                item for item in target_tree[i] if len(set(adjacency_list[item]).intersection(target_tree[i + 1])) > 0
            )

    tree = {0: {source_id}, len(source_tree) + len(target_tree) + 1: {target_id}}
    for i in range(len(source_tree)):
        tree[i + 1] = source_tree[i]
    for i in range(len(target_tree)):
        tree[i + len(source_tree) + 1] = target_tree[len(target_tree) - (i + 1)]

    return source_tree, target_tree, tree


if __name__ == "__main__":
    base_url = "http://localhost:8080/"
    get_publication_author_by_author_id = base_url + "publicationAuthor/findByAuthorId"
    get_author_by_name_like = base_url + "author/findByNameLike"
    get_author_by_id = base_url + "author/findById"
    get_publication_author_by_publication_id = base_url + "publicationAuthor/findByPublicationId"

    with open(r"H:\adjacency_list.pkl", "rb") as f:
        adjacency_list = pickle.load(f)

    params = {"firstname": "P", "lastname": "Trusov"}
    source = get_data(get_author_by_name_like, params)
    source = author_preparation(source[0])

    params = {"id": 666}
    target = get_data(get_author_by_id, params)
    target = author_preparation(target)

    k = 10
    source_id = 54004
    target_id = 208096
    # source_tree, target_tree, inter = find_tree_from_list(adjacency_list, source_id, target_id, k)
    # if inter is not None:
    #     source_tree, target_tree, tree = unpack(adjacency_list, source_tree, target_tree, inter, source_id, target_id)
    # else:
    #     tree = target_tree[0]

    import numpy as np
    import os
    import torch

    from tqdm import tqdm
    from itertools import repeat

    root_path = r"H:\streamlit_data"
    embedding_idxs_path = os.path.join(root_path, "idxs_all-mpnet-base-v2.pt")
    embedding_idxs = torch.load(embedding_idxs_path)
    np.random.seed(42)
    embedding_idxs1 = np.random.choice(embedding_idxs, size=4000)
    np.random.seed(43)
    embedding_idxs2 = np.random.choice(embedding_idxs, size=2000)

    okr = []
    for source, target_id in tqdm(zip(repeat(embedding_idxs1, len(embedding_idxs2)), embedding_idxs2)):
        for source_id in source:
            source_tree, target_tree, inter = find_tree_from_list(adjacency_list, source_id, target_id, 5)
            if inter is not None:
                okr.append(len(source_tree.keys()) + len(target_tree.keys()))


    # ent = []
    # for idx in tqdm(embedding_idxs):
    #     ent.append(find_tree_for_stats(adjacency_list, idx, k))


    # t1 = time.perf_counter()
    # entourages, inter = find_tree_from_list(adjacency_list, 208096, 54004, 3)
    # print(time.perf_counter() - t1)
    # print(len(inter))

    # nodes = create_nodes(inter)
    #
    # publication_author_path = r"H:\data lake\entities\publication_author.parquet"
    # publication_author_data = pd.read_parquet(publication_author_path)
    # edges = create_edges(inter, publication_author_data)


