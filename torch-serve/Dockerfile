# TODO: как сделать так, чтобы requirements был общий для fast-api and torch-serve? Иначе памяти много нужно
FROM pytorch/torchserve:0.8.2-gpu

COPY requirements.txt /torch-serve/requirements.txt
RUN python -m pip install --upgrade pip
RUN pip install --no-cache-dir --upgrade -r /torch-serve/requirements.txt

COPY all-mpnet-base-v2.mar model-store/
COPY
ENV DOCKER=true

CMD ["--host", "0.0.0.0", "--port", "3002"]

RUN python
