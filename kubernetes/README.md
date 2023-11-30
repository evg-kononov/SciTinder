minikube start

eval $(minikube docker-env)

docker compose -f docker-compose-simple.yml build