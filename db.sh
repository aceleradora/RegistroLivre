#!/bin/bash

sudo apt-get update
sudo apt-get install postgresql postgresql-contrib

sudo apt-get install maven

echo 'Digite login do banco'
read usuario

unset DB_CONNECTION_USUARIO
echo "export DB_CONNECTION_USUARIO=$usuario" >> ~/.bashrc


echo 'Digite senha do banco'
read senha
unset DB_CONNECTION_SENHA
echo "export DB_CONNECTION_SENHA=$senha" >> ~/.bashrc	

unset DB_CONNECTION_URL
echo 'export DB_CONNECTION_URL="jdbc:postgresql://localhost/registro_livre"' >> ~/.bashrc

sudo -u postgres psql -c "CREATE DATABASE registro_livre"

sudo -u postgres psql -c "CREATE USER $usuario WITH PASSWORD '$senha'"

exec bash
