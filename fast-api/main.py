from contextlib import asynccontextmanager

from fastapi import FastAPI, Request

resource = {}

@asynccontextmanager
async def app_lifespan(app: FastAPI):
    print("init lifespan")
    resource["msg"] = "Hello, it's beautiful day!!"
    yield
    resource.clear()
    print("clean up lifespan")

app = FastAPI(lifespan=app_lifespan)

@app.get("/")
async def root():
    print(resource)
    return resource

print("completed app init.")
print(resource)