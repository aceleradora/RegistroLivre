<%@ page language="java" contentType="text/html; charset=iso-8859-1"
	pageEncoding="iso-8859-1"%>
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
		$('#minhaTable').DataTable({
			data : <c:out value="${resultadoBusca}" escapeXml='false' />,
			columns : [ { data : 'nomeFantasia'	         }, 
			            { data : 'dataEmissaoDocumento'  },
			            { data : 'endereco.logradouro'			     },
			          ]
		});
		});
	</script>
</head>
<body>
	<jsp:include page="/WEB-INF/jsp/includes/cabecalho.jsp" />
	<div class="container">
		<div class="row">
			<div class="col-lg-6 col-lg-offset-3">
				<div class="panel panel-default margin-35-0">
					<div class="panel-heading">
						<h1>Lista de Empresas Cadastradas</h1>
						<table id="minhaTable" class="display">
							<thead>
								<tr>
									<th>Nome Fantasia</th>
									<th>Data Emissão Documento</th>
									<th>Endereço</th>
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