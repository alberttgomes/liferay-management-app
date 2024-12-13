#!/bin/bash

echo "Deploying modules..."

cd modules/ || exit; blade gw clean deploy; cd ../

echo "Starting docker services..."

docker compose down

docker compose up --build