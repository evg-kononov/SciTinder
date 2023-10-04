FROM python:3.10.9

WORKDIR /fast-api

COPY requirements.txt /fast-api/requirements.txt
RUN pip install --no-cache-dir --upgrade -r /fast-api/requirements.txt
RUN pip install torch --no-cache-dir --index-url https://download.pytorch.org/whl/cpu

COPY . .
# TODO: приложениен не должно знать где оно запущено
ENV DOCKER=true
ENTRYPOINT ["uvicorn", "server:app"]
CMD ["--host", "0.0.0.0", "--port", "3002"]
