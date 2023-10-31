import asyncio
import requests
import torch
from entourage_search import async_get_data_from_list


def find_by_id(author_idxs: list, url: str):
    urls = [url.format(author_id) for author_id in author_idxs]
    data = asyncio.run(async_get_data_from_list(urls))
    return data


def find_by_name_like_IDS(filter, base_url: str):
    query_params = dict(
        firstname="",
        lastname="",
        organizationId=filter.organization_ids[0],
        hIndexMin=filter.min_h_index,
        hIndexMax=filter.max_h_index,
        page=0,
        size=10000
    )
    url = base_url.format(**query_params)
    response = requests.get(url)
    total_pages = response.json()["totalPages"]
    urls = []
    for page in range(1, total_pages):
        query_params["page"] = page
        urls.append(base_url.format(**query_params))
    data = asyncio.run(async_get_data_from_list(urls))
    return data


def dot_score(a: torch.Tensor, b: torch.Tensor):
    """
    Computes the dot-product dot_prod(a[i], b[j]) for all i and j.
    :return: Matrix with res[i][j]  = dot_prod(a[i], b[j])
    """
    if not isinstance(a, torch.Tensor):
        a = torch.tensor(a)

    if not isinstance(b, torch.Tensor):
        b = torch.tensor(b)

    if len(a.shape) == 1:
        a = a.unsqueeze(0)

    if len(b.shape) == 1:
        b = b.unsqueeze(0)

    return torch.mm(a, b.transpose(0, 1))


