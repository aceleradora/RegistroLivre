#!/bin/bash

sudo apt-get update
sudo apt-get install postgresql postgresql-contrib

sudo apt-get install maven

echo 'export DB_CONNECTION_URL="jdbc:postgresql://localhost/registro_livre"' >> ~/.bashrc

source ~/.bashrc

sudo -u postgres psql -c "CREATE DATABASE registro_livre"

sudo -u postgres psql -c "CREATE USER mezxofwrhzixqp WITH PASSWORD 'U3wNSZNZ7rdOW4BVvw7x2ydYZb'"

