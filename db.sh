#!/bin/bash
##Script de instalação

sudo apt-get update
sudo apt-get install postgresql postgresql-contrib

sudo apt-get install maven

if [ -z "$DB_CONNECTION_USUARIO" ] && [ -z "$DB_CONNECTION_SENHA"]; then
	echo '[DIGITE O USUARIO DO BANCO]:'
	read usuario
	echo "export DB_CONNECTION_USUARIO=$usuario" >> ~/.bashrc

	echo '[DIGITE A SENHA DO BANCO]:'
	read senha
	echo "export DB_CONNECTION_SENHA=$senha">> ~/.bashrc

	sudo -u postgres psql -c "CREATE USER $usuario WITH PASSWORD '$senha'"
fi

if [ -z "$DB_CONNECTION_URL" ]; then
	echo 'export DB_CONNECTION_URL="jdbc:postgresql://localhost/registro_livre"' >> ~/.bashrc
fi


if [ -z "$API_SECRET" ]; then
	echo '[DIGITE O SECRET DO CLOUDINARY]:'
	read api_secret
	echo "export API_SECRET=$api_secret" >> ~/.bashrc
fi

if [ -z  "$API_KEY" ]; then
	echo '[DIGITE O API KEY DO CLODUINARY]:'
	read api_key
	echo "export API_KEY=$api_key" >> ~/.bashrc
fi

if [ -z "$CLOUD_NAME" ]; then
	echo '[DIGITE O CLOUD NAME DO CLOUDINARY]:'
	read cloud_name
	echo "export CLOUD_NAME=$cloud_name">>~/.bashrc
fi

exec bash
