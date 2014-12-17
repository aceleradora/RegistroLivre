<%@ page language="java" contentType="text/html; charset=iso-8859-1" pageEncoding="iso-8859-1"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="pt-br">
<head>
<meta charset="iso-8859-1" />
	<link href="/assets/css/bootstrap/css/bootstrap.css" rel="stylesheet">
	<link href="/assets/css/main.css" rel="stylesheet">
	<link href="http://cdn.datatables.net/1.10.4/css/jquery.dataTables.css" rel="stylesheet">
	
	<script src="http://code.jquery.com/jquery-2.1.1.min.js"></script>
	<script src="/assets/js/listagem.js"></script>
	
	<script src="http://cdn.datatables.net/1.10.4/js/jquery.dataTables.min.js"></script>
	<script type="text/javascript">
	
	<c:set var="resultadoBusca" value="${resultadoBusca}"/>
	
	$(document).ready(function() {
		criaDatatable(<c:out value="${resultadoBusca}" escapeXml='false' />);
	});
	
	</script>
</head>
<body>
	<jsp:include page="/WEB-INF/jsp/includes/cabecalho.jsp" />
	<div class="container">
		<div class="row">
			<div class="col-lg-8 col-lg-offset-2">
				<div class="panel panel-default margin-35-0">
					<div class="panel-heading">
						<h1>Lista de Empresas Cadastradas</h1>
					</div>
					<div class="panel-body">	
						<table id="tabelaListagem" class="table-striped table-hover">
							<thead>
								<tr>
									<th>Nome Fantasia</th>
									<th>Endereço</th>
									<th>Data Emissão Documento</th>
								</tr>
							</thead>
						</table>						
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>