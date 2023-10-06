import numpy as np
import os

import pandas as pd
import torch
import pickle

from tqdm import tqdm
from itertools import repeat
from util import *
from visualization import *
from multiprocessing import Pool


def get_required_idxs(author_df, organization_df, organization_name):
    """Позволяет получить индексы авторов, которые входят в указанную организацию

    :param author_df: сущность "author"
    :param organization_df: сущность "organization"
    :param organization_name: название организации для которой считается статистика
    :return: required_idxs: список требуемых индексов
    """

    required_idx = organization_df[organization_df["name"] == organization_name].index.values[0] + 1
    author_df = author_df[author_df["organization_id"] == float(required_idx)]
    required_idxs = author_df.index.values + 1
    return required_idxs


def entourage_coverage(adjacency_list, required_idxs=None, k=5):
    """
    :param adjacency_list: список смежности (словарь, где key - индекс автора, value - индексы соавторов)
    :param required_idxs: список требуемых индексов авторов для подсчета статистики
    :param k: порядок окружение, вплоть до которого производится расчет статистики
    :return:
    """

    if required_idxs is None:
        required_idxs = list(adjacency_list.keys())

    source_entourages = []
    for source_id in tqdm(required_idxs):
        tree = find_entourage_for_stats(adjacency_list, source_id, k)
        source_entourages.append(tree)
    return source_entourages


def handshake_distribution(adjacency_list, required_idxs=None, k=10):
    """

    :param adjacency_list: список смежности (словарь, где key - индекс автора, value - индексы соавторов)
    :param required_idxs: список требуемых индексов авторов для подсчета статистики
    :param k: порядок окружение, вплоть до которого производится расчет статистики (количество bins в распределении)
    :return:
    """
    if required_idxs is None:
        required_idxs = list(adjacency_list.keys())

    handshake_number = []
    for source_id in tqdm(required_idxs):
        with Pool() as pool:
            temp = list(pool.starmap(
                parallel_find_tree_from_list,
                zip(repeat(adjacency_list), repeat(source_id), list(adjacency_list.keys()), repeat(k // 2))
            ))
        handshake_number.append(temp)
        """
        for target_id in list(adjacency_list.keys()):
            source_tree, target_tree, inter = find_tree_from_list(adjacency_list, source_id, target_id, k // 2)
            if inter is not None:
                handshake_number.append(len(source_tree.keys()) + len(target_tree.keys()))
        """

    return handshake_number


if __name__ == "__main__":
    root_path = r"C:\data\streamlit_data"
    root_path_parquet = r"C:\data\data lake\entities"
    adjacency_list_path = os.path.join(root_path, "adjacency_list.pkl")
    embedding_idxs_path = os.path.join(root_path, "idxs_all-mpnet-base-v2.pt")
    author_path = os.path.join(root_path_parquet, "author.parquet")
    organization_path = os.path.join(root_path_parquet, "organization.parquet")

    author_df = pd.read_parquet(author_path)
    organization_df = pd.read_parquet(organization_path)
    with open(adjacency_list_path, "rb") as f:
        adjacency_list = pickle.load(f)
    embedding_idxs = torch.load(embedding_idxs_path)

    author_df = author_df.iloc[embedding_idxs - 1]
    organization_name = "PAWLIN Technologies LLC"

    required_idxs = get_required_idxs(author_df, organization_df, organization_name)

    #source_entourages = entourage_coverage(adjacency_list, required_idxs, 5)
    #plot_entourage_coverage(source_entourages)

    handshake_number = handshake_distribution(adjacency_list, required_idxs)




"""

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

"""
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