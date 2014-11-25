<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="assets/css/bootstrap/css/bootstrap.css" rel="stylesheet">
<link href="assets/css/main.css" rel="stylesheet">
</head>
<body>

	<nav class="navbar navbar-default" role="navigation">
	<div class="container-fluid">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="/">Home</a>
		</div>
		<div class="collapse navbar-collapse"
			id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav btn-borders-navbar">
				<li><a href="cadastro">Cadastrar Empresa</a></li>
				<li><a href="visualizacao/0">Visualizar Empresa</a></li>
				<li><a href="listagem">Listar Empresas</a></li>
			</ul>
		</div>
	</div>
	</nav>

	<div class="container">
		<div class="row">
			<div class="col-lg-6 col-lg-offset-3">
				<div class="panel panel-default margin-35-0">

					<div class="panel-heading">
						<h1>Lista de Empresas Cadastradas</h1>
					</div>

					<div class="list-group">
						<c:forEach items="${empresaList}" var="empresa">
							<div class="list-group-item">
								<a class="a-color-black" href="/visualizacao/${empresa.id}">
									<h4 class="list-group-item-heading" title="Nome fantasia">${empresa.nomeFantasia}</h4>
								</a>
								<p class="list-group-item-text">Endereço: ${empresa.endereco.logradouro}</p>
								<p class="list-group-item-text">Emissão do Documento: ${empresa.dataEmissaoDocumento}</p>
							</div>
						</c:forEach>
					</div>

					<div class="panel-footer">
						<h4>Total de Registros: ${totalDeRegistros}</h4>
						<a href="/">
							<button class="btn btn-md btn-primary pull-right">Voltar
							</button>
						</a>
						<div style="clear: both"></div>
					</div>

				</div>




				<!-- col -->
			</div>
			<!-- row -->
		</div>
		<!-- container -->
</body>
</html>