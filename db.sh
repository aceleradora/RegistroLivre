#!/bin/bash

sudo apt-get update
sudo apt-get install postgresql postgresql-contrib

sudo apt-get install maven

#se a variavel estiver vazia
if [ -z $DB_CONNECTION_USUARIO ]; then
	echo 'Digite login do banco'
	read usuario
	echo "export DB_CONNECTION_USUARIO=$usuario" >> ~/.bashrc
else
	$usuario = $DB_CONNECTION_USUARIO
fi

if [ -z $DB_CONNECTION_SENHA ]; then
	echo 'Digite senha do banco'
	read senha
	echo "export DB_CONNECTION_SENHA=$senha" >> ~/.bashrc	
else
	$senha = $DB_CONNECTION_SENHA
fi

echo 'export DB_CONNECTION_URL="jdbc:postgresql://localhost/registro_livre"' >> ~/.bashrc

sudo -u postgres psql -c "CREATE DATABASE registro_livre"

sudo -u postgres psql -c "CREATE USER $usuario WITH PASSWORD '$senha'"

exec bash
