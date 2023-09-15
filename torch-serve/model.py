import torch
from sentence_transformers import SentenceTransformer, util


def create_model(model_name, adapter_name=None, device="cpu"):
    """
    model_name - example: "all-mpnet-base-v2"; "allenai/specter2"
    adapter_name - example: None; "allenai/specter2_proximity"
    """
    model = SentenceTransformer(model_name).to(device)
    if adapter_name is not None:
        next(
            next(
                model.modules()
            )[0].children()
        ).load_adapter(adapter_name, source="hf", load_as="specter2_proximity", set_active=True)
    return model


if __name__ == "__main__":
    device = torch.device("cuda" if torch.cuda.is_available() else "cpu")
    model_name = "all-mpnet-base-v2"
    model_gpu = create_model(model_name=model_name)
    model_gpu.save(model_name)
    # script_model_gpu = torch.jit.script(model_gpu)
