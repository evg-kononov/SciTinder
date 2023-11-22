import redis
import uvicorn
import time
import random
from typing import List, Annotated, Union
from fastapi import FastAPI, Query
from rq import Queue
from rq.job import Job
from rq.registry import StartedJobRegistry
from config import *

r = redis.Redis(host=REDIS_HOST, port=REDIS_PORT, decode_responses=True)

app = FastAPI()

players = "Players"
value = [0]
@app.get("/counter")
def counter():
    value[0] += 1
    return value[0]


key = "counter"
r.set(key, "0")
@app.get("/counter-redis")
def counter():
    r.incr(key)
    return r.get(key)


@app.get("/leaderboard/range")
def view(
        start: Annotated[int, Query()] = 0,
        end: Annotated[int, Query()] = -1
):
    return r.zrange(players, start, end, desc=True, withscores=True)


@app.put("/leaderboard/update")
def view(
        player: Annotated[str, Query()],
        score: Annotated[int, Query()]
):
    return r.zadd(players, {player: score}, xx=True)


@app.put("/leaderboard/create")
def view(
        player: Annotated[str, Query()],
        score: Annotated[int, Query()]
):
    return r.zadd(players, {player: score}, nx=True)


@app.delete("/leaderboard/remove")
def remove(player: Annotated[Union[List[str], None], Query()] = None):
    return r.zrem(players, *player)


queue_name = "FileOps"
q = Queue(name=queue_name, connection=r)


def read(lines):
    return lines


@app.get("/queue/files/{file_path:path}")
def read_file(file_path: str):
    with open(file_path) as f:
        lines = f.readlines()
    time.sleep(random.randrange(1, 10))
    job = q.enqueue(read, lines)
    return job.id


@app.get("/queue/process")
def read_file(job_id: str):
    time.sleep(random.randrange(1, 10))
    job = Job.fetch(job_id, connection=r)
    result = job.return_value()
    return result


@app.get("/queue/job-ids")
def read_file():
    registry = StartedJobRegistry(queue_name, connection=r)
    return registry.get_job_ids()


if __name__ == "__main__":
    uvicorn.run(app, host="127.0.0.1", port=8009)
