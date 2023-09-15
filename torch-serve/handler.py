import os
import json
import zipfile
import torch
import numpy as np

from json import JSONEncoder
from sentence_transformers import SentenceTransformer


class NumpyArrayEncoder(JSONEncoder):
    def default(self, obj):
        if isinstance(obj, np.ndarray):
            return obj.tolist()
        return JSONEncoder.default(self, obj)


class SentenceTransformerHandler(object):
    def __init__(self):
        super().__init__()
        self.batch_size = None
        self.model_path = None
        self.manifest = None
        self.device = None
        self.map_location = None
        self.embedder = None
        self.initialized = False

    def initialize(self, context):
        properties = context.system_properties
        model_dir = properties.get("model_dir")
        self.batch_size = properties.get("batch_size")
        self.map_location = (
            "cuda"
            if torch.cuda.is_available() and properties.get("gpu_id") is not None
            else "cpu"
        )
        self.device = torch.device(
            self.map_location + ":" + str(properties.get("gpu_id"))
            if torch.cuda.is_available() and properties.get("gpu_id") is not None
            else "cpu"
        )

        self.manifest = context.manifest
        serialized_file = self.manifest["model"]["serializedFile"]
        model_name = self.manifest["model"]["modelName"]
        self.model_path = os.path.join(model_dir, serialized_file)

        if self.model_path.endswith(".zip") or self.model_path.endswith(".bin"):
            try:
                with zipfile.ZipFile(self.model_path, "r") as zip_ref:
                    zip_ref.extractall(model_dir)
            except Exception as ex:
                raise Exception("Failed to unpack the archive with the model. " + str(ex))

        self.embedder = SentenceTransformer(os.path.join(model_dir, model_name), device=self.device)
        self.embedder.eval()
        self.initialized = True

    def preprocess(self, requests):
        """
        requests - a list containing a dictionary, might be in the form
                   of [{'body': json_file}] or [{'data': json_file}]
        """

        data = requests[0].get("data")
        if data is None:
            data = requests[0].get("body")

        data = data.decode("utf-8")  # Decode <class 'bytearray'>
        data = json.loads(data)  # Parse <class 'str'>
        sentences = data["queries"]
        return sentences

    def inference(self, query):
        with torch.no_grad():
            query_embeddings = self.embedder.encode(
                sentences=query,
                batch_size=self.batch_size,
                device=self.device,
                normalize_embeddings=True
            )
        return query_embeddings

    def postprocess(self, model_output):
        return [json.dumps(model_output, cls=NumpyArrayEncoder)]
