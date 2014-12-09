<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta charset="UTF-8" />
	
	<title>Registro Livre - A verdade está lá fora!</title>
	
	<link href="assets/css/bootstrap/css/bootstrap.css" rel="stylesheet">
	<link href="assets/css/bootstrap/css/bootstrap-theme.css" rel="stylesheet">
	<link href="assets/css/main.css" rel="stylesheet">
	
	<script src="http://code.jquery.com/jquery-2.1.1.min.js"></script>
	<script src="../assets/js/visualizacao.js"></script>
</head>

<body>
	<jsp:include page="/WEB-INF/jsp/includes/cabecalho.jsp" />	
	<div class="container">
		
		<div class="row">
			<div class="col-lg-6 col-lg-offset-3">
				<c:if test="${listaDeResultadosDeEmpresasVazia == true}">
					<div class="alert alert-warning alert-dismissible" role="alert">
						<button type="button" class="close" id="close"
							data-dismiss="alert">
							<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
						</button>
						<strong>Não há nenhum registro de empresa para a busca
							efetuada.</strong>
					</div>
				</c:if>
				<div class="panel panel-default margin-100-0-0-0 transparente">
					<div class="panel-heading centralize">
						<h1 id="registrolivre">Registro Livre</h1>
					</div>
					<div class="panel-body">
						<form class="form" name="pesquisa" action="/busca" method="GET">
							<div class="form-group">
								<input class="form-control" type="text" name="q" id="campoPesquisado" />
							</div>
							<div class="form-group">
								<input type="submit" id="btn-submit" class="btn btn-lg btn-primary pull-right margin-0-6" value="Buscar"/>
							</div>
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