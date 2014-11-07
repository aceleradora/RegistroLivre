<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
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
						<h1>Visualização de Empresa</h1>
					</div>


					<div class="panel-body">
						<div class="form-group">
							<label class="control-label">CNPJ:</label> <label
								class="control-label">${empresa.cnpj}</label>
						</div>

						<div class="form-group">
							<label class="control-label">Nome fantasia:</label> <label
								class="control-label">${empresa.nomeFantasia}</label>
						</div>

						<div class="form-group">
							<label class="control-label">Razão Social:</label> <label
								class="control-label">${empresa.razaoSocial}</label>
						</div>

						<div class="form-group">
							<label class="control-label">Endereço:</label> <label
								class="control-label">${empresa.endereco}</label>
						</div>

						<div class="form-group">
							<label class="control-label">Número:</label> <label
								class="control-label">${empresa.numero}</label>
						</div>

						<div class="form-group">
							<label class="control-label">Complemento:</label> <label
								class="control-label">${empresa.complemento}</label>
						</div>

						<div class="form-group">
							<label class="control-label">Cidade:</label> <label
								class="control-label">${empresa.cidade}</label>
						</div>

						<div class="form-group">
							<label class="control-label">Estado:</label> <label
								class="control-label">${empresa.estado}</label>
						</div>

						<div class="form-group">
							<label class="control-label">CEP:</label> <label
								class="control-label">${empresa.cep}</label>
						</div>

						<div class="form-group">
							<label class="control-label">Data de abertura:</label> <label
								class="control-label">${empresa.dataAbertura}</label>
						</div>

						<div class="form-group">
							<label class="control-label">Emissão de documento:</label> <label
								class="control-label">${empresa.emissaoDocumento}</label>
						</div>

					</div>
					<div class="panel-heading">
						<h1>Estrutura Societária</h1>
					</div>
					<div class="panel-body">
						<c:forEach items="${empresa.socios}" var="socio">
							<label class="control-label">Sócio ${socio.id + 1}: </label>
							<label class="control-label">${socio.nome}</label>
							<label class="control-label">- CPF: </label>
							<label class="control-label">${socio.cpf}</label>
							<c:if test="${socio.situacaoDoSocio == true}">
								<span class="label label-success">Ativo</span>
							</c:if>
							<c:if test="${socio.situacaoDoSocio == false}">
								<span class="label label-important">Inativo</span>
							</c:if>
						</c:forEach>
					</div>

					<div class="panel-footer">
						<form action="###">
							<input type="submit"
								class="btn btn-lg btn-primary pull-right margin-0-6"
								value="Voltar" />
						</form>
						<div style="clear: both"></div>
					</div>
					<!-- panel -->
				</div>
				<!-- col -->
			</div>
			<!-- row -->
		</div>
		<!-- container -->
</body>
</html>