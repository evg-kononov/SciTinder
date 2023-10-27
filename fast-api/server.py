import redis
import uvicorn
from typing import List, Annotated, Union
from fastapi import FastAPI, Query
from config import *

r = redis.Redis(host=REDIS_HOST, port=REDIS_PORT, decode_responses=True)

app = FastAPI()

players = "Players"
value = [0]
@app.get("/counter/")
def counter():
    value[0] += 1
    return value[0]


key = "counter"
r.set(key, "0")
@app.get("/counter-redis/")
def counter():
    r.incr(key)
    return r.get(key)


@app.get("/leaderboard/range/")
def view(
        start: Annotated[int, Query()] = 0,
        end: Annotated[int, Query()] = -1
):
    return r.zrange(players, start, end, desc=True, withscores=True)


@app.post("/leaderboard/add/")
def view(
        player: Annotated[str, Query()],
        score: Annotated[int, Query()]
):
    return r.zadd(players, {player: score})


@app.delete("/leaderboard/remove/")
def remove(player: Annotated[Union[List[str], None], Query()] = None):
    return r.zrem(players, *player)



if __name__ == "__main__":
    uvicorn.run(app, host="127.0.0.1", port=8009)
