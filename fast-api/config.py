import os

knees_num = 5
top_k = 10

# TODO: нужно написать REDIS с учетом DOCKER'a
REDIS_HOST = "localhost"
REDIS_PORT = 6379
# TODO: нужно написать API с учетом DOCKER'a
SBERT_API = "http://localhost:8080"
SBERT_NAME = "all-mpnet-base-v2"
SBERT_PREDICT_URL = SBERT_API + "/predictions/" + SBERT_NAME

if not os.getenv("DOCKER"):
    api = "http://localhost:8080/"
else:
    api = f"http://api:{os.getenv('API_PORT', 3001)}/"
get_author_by_id = api + "author/findById" + "?id={}"

root_path = r"./data"
adjacency_list_path = os.path.join(root_path, "adjacency_list.pkl")
publication_author_path = os.path.join(root_path, "publication_author.parquet")
corpus_embeddings_path = os.path.join(root_path, "embeddings_all-mpnet-base-v2.pt")
embedding_idxs_path = os.path.join(root_path, "idxs_all-mpnet-base-v2.pt")