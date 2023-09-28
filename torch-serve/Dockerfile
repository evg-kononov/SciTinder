# TODO: как сделать так, чтобы requirements был общий для fast-api and torch-serve? Иначе памяти много нужно
# TODO: проверить сборку на -gpu
FROM pytorch/torchserve:0.8.2-cpu

WORKDIR /torch-serve

COPY requirements.txt /torch-serve/requirements.txt
COPY config.properties /torch-serve/config.properties
COPY all-mpnet-base-v2.mar /torch-serve/model_store/all-mpnet-base-v2.mar
RUN chmod 777 /torch-serve/model_store/all-mpnet-base-v2.mar
RUN python -m pip install --upgrade pip
RUN pip install --no-cache-dir --upgrade -r /torch-serve/requirements.txt

# TODO: предполагается, что .mar лежит в папке, где Dockerfile (можно автоматизировать создание .mar)
ENV DOCKER=true

#ENTRYPOINT ["torchserve"]
CMD ["torchserve", "--start", "--ts-config", "config.properties", "--ncs"]