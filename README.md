![alt tag](http://i.imgur.com/CguDLcL.png?1)

# Registro Livre

O **Registro Livre** é uma plataforma de dados abertos cujo objetivo principal é colocar à disposição do público informações sobre empresas, imóveis e outros tipos de bens e atividades sujeitos a registro público no Brasil. Embora os dados disponíveis em cartórios e juntas comerciais sejam públicos, eles raramente são publicados. O cidadão que deseja ter acesso a contratos sociais e informações sobre propriedade de imóveis, por exemplo, precisa pagar taxas e aguardar vários dias para receber cópias de documentos. O Registro Livre vai reunir estes dados numa plataforma aberta, para a qual todo cidadão poderá contribuir. Desta forma, os registros públicos serão acessíveis a todos e tornados públicos de fato.

## Status
[![Build Status](https://snap-ci.com/aceleradora6-tw/RegistroLivre/branch/master/build_image)](https://snap-ci.com/aceleradora6-tw/RegistroLivre/branch/master)

## Tecnologias e dependências
* JAVA [(VRaptor3 Framework)](http://vraptor3.vraptor.org/pt/)
* PostgreSQL
* Hibernate
* Jetty
* Maven

##### Testes
* JUnit
* Hamcrest
* Selenium / Fluentlenium
* Mockito

##### DevOps
* [SnapCI](https://snap-ci.com/aceleradora6-tw/RegistroLivre/branch/master)

##### Hospedagem
* [Heroku Staging](http://registro-livre-staging.herokuapp.com/)
* [Heroku Produção](http://registro-livre-aceleradora.herokuapp.com/)
* [Cloudinary (Arquivos .pdf)](http://cloudinary.com/)
*É indicado ter uma conta no Cloudinary para desenvolvimento local e gestão dos arquivos PDF. (A configuração padrão no ambiente de produção é no Cloudinary e está configurada no arquivo [db.sh](https://github.com/aceleradora6-tw/RegistroLivre/blob/master/db.sh))*


## Para rodar execute o comando no terminal 

	$ mvn jetty:run
	
## Configurando o ambiente	

### Mac

No mac, você precisa ter as seguintes tecnologias instaladas:

1. [Ruby](https://www.ruby-lang.org/pt/downloads/)
2. [Java 1.7+](http://www.oracle.com/technetwork/pt/java/javase/downloads/jdk7-downloads-1880260.html)

Para fazer o projeto funcionar, você precisa rodar o arquivo 'dbMac.sh' para baixar e configurar o banco local postgresql e suas variaveis.

### Debian/Ubuntu

No Debian/Ubuntu, você precisa ter as seguintes tecnologias instaladas:

1. [Java 1.7+](http://www.oracle.com/technetwork/pt/java/javase/downloads/jdk7-downloads-1880260.html)

Para fazer o projeto funcionar, você precisa rodar o arquivo 'db.sh' para baixar e configurar o banco local postgresql e suas variaveis.

Para usar o projeto em alguma IDE importe-o como um projeto Maven para o seu workspace.
