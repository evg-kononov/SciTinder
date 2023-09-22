# TODO: как сделать так, чтобы requirements был общий для fast-api and torch-serve? Иначе памяти много нужно
# TODO: проверить сборку на -gpu
FROM pytorch/torchserve:0.8.2-cpu

COPY requirements.txt /torch-serve/requirements.txt
RUN python -m pip install --upgrade pip
RUN pip install --no-cache-dir --upgrade -r /torch-serve/requirements.txt

# TODO: предполагается, что .mar лежит в папке, где Dockerfile (можно автоматизировать создание .mar)
COPY all-mpnet-base-v2.mar ./model_store
ENV DOCKER=true

ENTRYPOINT ["torchserve"]
CMD ["--start", "--ts-config", "config.properties", "--ncs"]
