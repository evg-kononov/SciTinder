from handler import SentenceTransformerHandler

_service = SentenceTransformerHandler()


def handle(request, context):
    """Entry point for SentenceTransformerHandler handler

    request - the input data from the incoming request
    context - is the TorchServe context: https://github.com/pytorch/serve/blob/master/ts/context.py

    """
    try:
        if not _service.initialized:
            _service.initialize(context)
            print("ENTERING INITIALIZATION")
        if request is None:
            return None
        model_input = _service.preprocess(request)
        model_output = _service.inference(model_input)
        response = _service.postprocess(model_output)
        return response
    except Exception as ex:
        raise Exception("Unable to process input data. " + str(ex))
