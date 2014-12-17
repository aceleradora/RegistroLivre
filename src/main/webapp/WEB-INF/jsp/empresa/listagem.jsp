<%@ page language="java" contentType="text/html; UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="pt-br">
<head>	
	<title>Listar Empresas</title>
	<jsp:include page="/WEB-INF/jsp/includes/assets.jsp" />
	<script src="../assets/js/jquery.mask.min.js"></script>
	
	<jsp:include page="/WEB-INF/jsp/includes/datatable.jsp" />
	<script src="../assets/js/listagem.js"></script>
	
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