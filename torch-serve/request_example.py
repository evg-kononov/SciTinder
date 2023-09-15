import requests
import json
import numpy as np

sentences = ["Hello world!"]
sbert_api = "http://localhost:8080"

response = requests.post(sbert_api + "/predictions/all-mpnet-base-v2",
                         data={"data": json.dumps({"queries": sentences})})

if response.status_code == 200:
    query_embeddings = response.json()
    print(query_embeddings)
