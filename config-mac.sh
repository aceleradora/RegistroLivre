#!/bin/bash

#instalação do Homebrew
ruby -e "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/master/install)"

#instala o Postgres pelo Homebrew

brew install postgresql

#configurações do Postgres
initdb /usr/local/var/postgres
mkdir -p ~/Library/LaunchAgents
ln -sfv /usr/local/opt/postgresql/*.plist ~/Library/LaunchAgents
launchctl load ~/Library/LaunchAgents/homebrew.mxcl.postgresql.plist

#cria o banco “registro_livre”
createdb registro_livre

#instalação do Maven
brew install maven

#se a variável estiver vazia
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


#configuração das variáveis de ambiente
echo 'export DB_CONNECTION_URL="jdbc:postgresql://localhost/registro_livre"' >> ~/.bashrc
export JAVA_HOME=`/usr/libexec/java_home`

#cria o usuário no banco
psql -c "CREATE USER $usuario WITH PASSWORD '$senha'"

exec bash