minikube start

eval $(minikube docker-env)

docker compose -f docker-compose-simple.yml build

kubectl apply -f kubernetes/service.yml

kubectl apply -f kubernetes/deployment.yml

kubectl get all

kubectl logs deployment/counter-deployment

minikube service counter-service