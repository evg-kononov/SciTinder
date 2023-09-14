import requests
from fastapi.testclient import TestClient
from server import app

client = TestClient(app)

def test_similarity_search_findById():
    response = client.post(url="/similarity-search/findById/", json={"source_id": 555})
    return response


def test_similarity_search_findByPrompt():
    response = client.post(
        url="/similarity-search/findByPrompt/",
        json={"prompt": ["Hello World!", "Hello PNRPU!", "Hello Math!"]}
    )
    return response


def test_filter_caching():
    response = client.post(
        url="/caching/filter/",
        json={
            "organizations": ["PNRPU", "MIPT", "MGU", "HSE"],
            "min_h_index": 1,
            "max_h_index": 10
        }
    )
    return response


def test_similarity_search_findByFile():
    response = client.post(
        url="/similarity-search/findByFile/",
        files=[("files", open("file.txt", "rb"))]
    )
    return response

