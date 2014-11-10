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
						<c:forEach items="${empresaList}" var="empresa">
							<a class="a-color-black" href="/visualizacao">
								<div class="list-group-item">
									<h4 class="list-group-item-heading">Nome Fantasia:
										${empresa.nomeFantasia}</h4>
									<p class="list-group-item-text">Endereço: ${empresa.endereco}</p>
									<p class="list-group-item-text">Emissão do Documento:
										${empresa.dataEmissaoDocumento}</p>
							</a>
						</c:forEach>
					</div>

				</div>

				<div class="panel-footer">
					<h4>Total de Registros: 1</h4>
					<a href="/">
						<button class="btn btn-md btn-primary pull-right">Voltar
						</button>
					</a>
					<div style="clear: both"></div>
				</div>


				<!-- col -->
			</div>
			<!-- row -->
		</div>
		<!-- container -->
</body>
</html>