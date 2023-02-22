#!/bin/bash

set -e

echo "Starting Docker services..."
docker compose stop && docker compose up &> docker-services.log &

r=`pwd`
echo $r

cd $r/account-service
echo "Starting Account Service..."
mvn -q clean spring-boot:run &> ../account-service.log &

echo "Starting Transaction Service..."
cd $r/transaction-service
mvn -q clean spring-boot:run &> ../transaction-service.log &

cd $r/bank-app
yarn && yarn build
echo "Starting Bank App..."
yarn start