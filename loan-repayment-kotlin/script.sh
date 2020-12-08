#!/usr/bin/env bash

DOCKER_APPLICATION_NAME=repayment

echo -e "\nStop running Docker containers with image tag ${CONTAINER_NAME}"

docker stop $(docker ps -a | grep ${DOCKER_APPLICATION_NAME} | awk '{print $1}')

echo -e "\n Removing Docker containers with image tag ${CONTAINER_NAME}"

docker rm $(docker ps -a | grep ${DOCKER_APPLICATION_NAME} | awk '{print $1}')

echo -e "\nSet Setting images name as ${DOCKER_APPLICATION_NAME}\n"

IMAGE_NAME=${DOCKER_APPLICATION_NAME}:dev

echo -e "\nSet Docker Image name as ${IMAGE_NAME}\n"

PORT=8080

echo -e "Setting the port as ${PORT}\n"

echo -e "Packaging the application...\n"

mvn clean package

echo -e "\nBuilding the docker image with name ${IMAGE_NAME}...\n"

docker build -t ${IMAGE_NAME} -f Dockerfile .

echo -e "\nStarting the docker image ${IMAGE_NAME} with name ${DOCKER_APPLICATION_NAME}...\n"

docker run --rm -i -p ${PORT}:${PORT} --name ${DOCKER_APPLICATION_NAME} ${IMAGE_NAME}