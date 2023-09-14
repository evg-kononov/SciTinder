FROM python:3.10.9

WORKDIR /fast-api

COPY ./requirements.txt /fast-api/requirements.txt
RUN pip install --no-cache-dir --upgrade -r /fast-api/requirements.txt
# RUN pip install torch torchvision torchaudio --index-url https://download.pytorch.org/whl/cu118

COPY ./fast-api /fast-api
# ENV DOCKER=true
CMD ["uvicorn", "server:app", "--host", "0.0.0.0", "--port", "80"]
