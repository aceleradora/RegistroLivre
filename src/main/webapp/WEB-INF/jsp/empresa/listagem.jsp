<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="assets/css/bootstrap.css" rel="stylesheet">
<link href="assets/css/main.css" rel="stylesheet">
</head>
<body>


	<div class="container">
		<div class="row">
			<div class="col-lg-6 col-lg-offset-3">
				<div class="panel panel-default margin-35-0">

					<div class="panel-heading">
						<h1>Lista de Empresas Cadastradas</h1>
					</div>

					<div class="list-group">

						<div class="list-group-item">
							<h4 class="list-group-item-heading">Nome Fantasia:
								${socio.id + 1}</h4>
							<p class="list-group-item-text">Endereço: ${socio.id + 1}</p>
							<p class="list-group-item-text">Numero: ${socio.id + 1}</p>
							<p class="list-group-item-text">Complemento: ${socio.id + 1}</p>
							<p class="list-group-item-text">Cidade: ${socio.id + 1}</p>
							<p class="list-group-item-text">Estado: ${socio.id + 1}</p>
							<p class="list-group-item-text">Cep: ${socio.id + 1}</p>
							<p class="list-group-item-text">Emissão do Documento:
								${socio.id + 1}</p>

						</div>

						<div class="list-group-item">
							<h4 class="list-group-item-heading">Nome Fantasia:
								${socio.id + 1}</h4>
							<p class="list-group-item-text">Endereço: ${socio.id + 1}</p>
							<p class="list-group-item-text">Numero: ${socio.id + 1}</p>
							<p class="list-group-item-text">Complemento: ${socio.id + 1}</p>
							<p class="list-group-item-text">Cidade: ${socio.id + 1}</p>
							<p class="list-group-item-text">Estado: ${socio.id + 1}</p>
							<p class="list-group-item-text">Cep: ${socio.id + 1}</p>
							<p class="list-group-item-text">Emissão do Documento:
								${socio.id + 1}</p>

						</div>

					</div>

					<div class="panel-footer">
						<h4>Total de Registros: 2</h4>
						<div style="clear: both"></div>
					</div>


					<!-- col -->
				</div>
				<!-- row -->
			</div>
			<!-- container -->
</body>
</html>