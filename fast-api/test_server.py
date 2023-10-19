import requests
from fastapi.testclient import TestClient
from server_main import app

client = TestClient(app)

def test_similarity_search_findById():
    response = client.post(url="/similarity-search/findById/", json={"source_id": 555})
    return response


def test_similarity_search_findByPrompt():
    response = client.post(
        url="/similarity-search/findByPrompt/",
        json={"top_k": 25,
            "prompt": ["In this work, we have carried out an experimental study of the process of vulcanization of rubber compounds. During the experiment, a disk-shaped sample is placed between the working surfaces of the rheometer. It is then heated to a predetermined temperature and exposed to an oscillating load with a set amplitude and frequency. The temporal dependence of torque at a set temperature is plotted. At the end of the tests, the temporal dependences of the curves of torque at given temperatures have been obtained. These dependences have been transformed into a family of the curves of the degree of vulcanization completion versus time and temperature. Based on the analysis of the vulcanization curves family, the temperature dependence of the vulcanization completion time for different brands of rubber compounds has been plotted. We compared three ethylene–propylene rubber brands. Using the experimental results, the dependence has been obtained with allowance for the factors of temperature and time. An iterative procedure to determine the coefficients involved in the regression expression describing the degree of vulcanization of rubbers has been developed, and the accuracy of the used expression has been estimated. We compared the experimental and calculated dependences of the crosslinking completion degree. The dependence of the regression expression coefficients on temperature and the brand of rubber has been plotted, and their physical meaning has been interpreted. The possibilities of using the results obtained in mathematical modelling of the process of technological vulcanization of products of cable insulation are described."]}
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


def test_crete_figure():
    response = client.post(
        url="/plotly/create-figure/",
        json={
            "source_id": 5,
            "target_id": 9
        }
    )
    return response

if __name__ == "__main__":
    response = test_similarity_search_findById()


