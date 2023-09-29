import redis
import uvicorn
from fastapi import FastAPI
from config import *

r = redis.Redis(host=REDIS_HOST, port=REDIS_PORT, decode_responses=True)

app = FastAPI()

value = [0]
@app.get("/counter")
def counter():
    value[0] += 1
    return value[0]


if __name__ == "__main__":
    uvicorn.run(app, host="127.0.0.1", port=8009)
