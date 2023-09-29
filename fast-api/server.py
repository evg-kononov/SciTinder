import json
import redis
import uuid

import uvicorn

import pandas as pd
import numpy as np
from contextlib import asynccontextmanager
from typing import Union, Annotated, List, Set

import requests
import torch
from fastapi import FastAPI, Body, UploadFile, File, HTTPException, Form
from pydantic import BaseModel, Field
from util import dot_score
from resources import *
from config import *
from entourage_search import author_preparation, async_get_data_from_list
from visualization import graph_visualization, graph_data_preparation, create_edges, create_nodes

r = redis.Redis(host=REDIS_HOST, port=REDIS_PORT, decode_responses=True)

app = FastAPI()

value = [0]
@app.get("/counter/")
def counter():
    value[0] += 1
    return value[0]


if __name__ == "__main__":
    uvicorn.run(app, host="127.0.0.1", port=8009)
