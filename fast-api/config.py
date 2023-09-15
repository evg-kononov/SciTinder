import os

TORCH_SERVE_MODEL_NAME = "all-mpnet-base-v2"

if not os.getenv("DOCKER"):
    REDIS_HOST = "localhost"
    REDIS_PORT = 6379

    TORCH_SERVE_API = "http://localhost:8080"
    TORCH_SERVE_PREDICT_URL = TORCH_SERVE_API + "/predictions/" + TORCH_SERVE_MODEL_NAME

    SPRING_API = "http://localhost:8080/"
else:
    REDIS_HOST = "redis"
    REDIS_PORT = os.getenv('REDIS_PORT', 6379)

    TORCH_SERVE_API = f"http://torch-serve:{os.getenv('TORCH_SERVE_API_PORT', 3004)}"
    TORCH_SERVE_PREDICT_URL = TORCH_SERVE_API + "/predictions/" + TORCH_SERVE_MODEL_NAME

    SPRING_API = f"http://spring-api:{os.getenv('SPRING_API_PORT', 3001)}/"

# get_author_by_id = SPRING_API + "author/findById" + "?id={}"

root_path = r"./data"
adjacency_list_path = os.path.join(root_path, "adjacency_list.pkl")
publication_author_path = os.path.join(root_path, "publication_author.parquet")
corpus_embeddings_path = os.path.join(root_path, "embeddings_all-mpnet-base-v2.pt")
embedding_idxs_path = os.path.join(root_path, "idxs_all-mpnet-base-v2.pt")