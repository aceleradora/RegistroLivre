<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Registro Livre - A verdade está lá fora teste!</title>
<link href="assets/css/bootstrap/css/bootstrap.css" rel="stylesheet">
<link href="assets/css/bootstrap/css/bootstrap-theme.css"
	rel="stylesheet">
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
			<ul class="nav navbar-nav">
				<li ><a href="cadastro">Cadastrar Empresa <span
						class="sr-only">(current)</span></a></li>
				<li><a href="visualizacao/0">Visualizar Empresa</a></li>
				<li><a href="listagem">Listar Empresas</a></li>
		</div>
	</div>
	</nav>
	
	<div class="container">
	
		<div class="row">
			<div class="col-lg-6 col-lg-offset-3">
				<div class="panel panel-default margin-100-0-0-0 transparente">
					<div class="panel-heading centralize">
						<h1 id="registrolivre">Registro Livre</h1>
					</div>
					<div class="panel-body">
						<form class="form" name="pesquisa" action="" method="POST">
							<input class="form-control" type="text" name="campoPesquisado"
								id="campoPesquisado" />

							<div class="radio">
								<label class="radio-inline"> 
									<input type="radio" name="tipoPesquisa" id="radioNomeFantasia" value="nomeFantasia" checked="true">
									Nome Fantasia
								</label> 
								<label class="radio-inline"> 
									<input type="radio" name="tipoPesquisa" id="radioCnpj" value="cnpj">
									CNPJ
								</label>
							</div>
							<input type="submit" id="btn-submit" class="btn btn-lg btn-primary pull-right margin-0-6" value="Buscar"/>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script src="http://code.jquery.com/jquery-2.1.1.min.js"></script>
	<script src="/assets/js/jquery.mask.min.js"></script>
	<script src="/assets/js/validacao-visual-home.js"></script>
</body>
</html>