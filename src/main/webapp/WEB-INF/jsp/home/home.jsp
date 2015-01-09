<%@ page language="java" contentType="text/html; charset=iso-8859-1" pageEncoding="iso-8859-1"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="pt-br">
<head>	
	<title>Registro Livre - A verdade está lá fora!</title>	
	<meta charset="iso-8859-1" />
	<link rel="stylesheet" href="//code.jquery.com/ui/1.11.2/themes/smoothness/jquery-ui.css">
	<link href="/assets/css/bootstrap/css/bootstrap.css" rel="stylesheet">	
	<link href="/assets/css/main.css" rel="stylesheet">	
	<script src="http://code.jquery.com/jquery-1.10.2.js"> </script> 
	<script src="http://code.jquery.com/ui/1.11.2/jquery-ui.js"></script>	
	<script src="/assets/js/home.js" charset="UTF-8"></script>
	<script type="text/javascript" src="http://cidades-estados-js.googlecode.com/files/cidades-estados-1.2-utf8.js"></script>
	<script src="/assets/js/jquery.mask.min.js"></script>		
	<script src="/assets/js/validacao-logica-cadastro.js"></script>		
	<script src="/assets/js/validacao-visual-cadastro.js"></script>
</head>

<body>
	<jsp:include page="/WEB-INF/jsp/includes/cabecalho.jsp" />	
	<div class="container content">
		
		<div class="row">
			<div class="col-lg-6 col-lg-offset-3">
				<c:if test="${listaDeResultadosDeEmpresasVazia == true}">
					<div class="alert alert-warning alert-dismissible" role="alert">
						<button type="button" class="close" id="close" onclick="fechaAlertaNenhumRegistro()"
							data-dismiss="alert">
							<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
						</button>
						<strong>Não há nenhum registro de empresa para a busca
							efetuada.</strong>
					</div>
				</c:if>
				

				<c:if test="${buscaVazia == true}">
					<div class="alert alert-warning alert-dismissible" role="alert">
						<button type="button" class="close" id="close"
							data-dismiss="alert">
							<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
						</button>
						<strong>Não há nada digitado no campo de busca, por favor tente novamente.</strong>
					</div>
				</c:if>
				
				<div class="panel panel-default margin-100-0-0-0 transparente">

					<div class="panel-heading centralize">
						<h1 id="registrolivre">Registro Livre</h1>
					</div>
					<div class="panel-body">
						<form class="form" name="pesquisa" action="/busca" method="GET">
							<div class="form-group ui-widget">
								<input class="form-control" type="text" name="busca" id="campoPesquisado" placeholder="Busque por uma empresa aqui" />
							</div>
							<div class="pull-left padding-30-0-0-0">
								<i class="padding-0-6"><a onclick="mostraBuscaAvancada()">Busca Avançada</a></i>
							</div>
							<div class="form-group">
								<input type="submit" id="btn-submit" class="btn btn-lg btn-primary pull-right margin-0-6" value="Buscar" disabled="true"/>
							</div>
						</form>
					</div>
				</div>
			</div>
			<div id="busca-avancada" class="col-lg-6 col-lg-offset-3" style="display:none">
				<div class="panel panel-default margin-30-0">
					<div class="panel-heading centralize">
						<h4>Busca Avançada</h4>
					</div>
					<div class="panel-body">
						<form class="form" name="pesquisaAvancada" action="???" method="GET">
							<div class="form-group">
								<label>CNPJ</label>
								<input type="text" id="cnpj-busca-avancada" class="form-control" name="cnpj"/>
							</div>
							<div class="form-group">
								<label>Nome Fantasia</label>
								<input type="text" class="form-control" name="nomeFantasia"/>
							</div>
							<div class="form-group">
								<label>Razão Social</label>
								<input type="text" class="form-control" name="RazaoSocial"/>
							</div>
							<div class="row">
								<div class="col-lg-3">
									<div class="form-group">
										<label>Estado</label>
										<input type="text" id="estado" class="form-control" name="estado"/>
									</div>
								</div>
								<div class="col-lg-9">
									<div class="form-group">
										<label>Cidade</label>
										<input type="text" id="cidade" class="form-control" name="cidade"/>
									</div>
								</div>
							</div>
							<div class="form-group">
								<label>Logradouro</label>
								<input type="text" class="form-control" name="logradouro"/>
							</div>
							<div class="form-group">
								<label>Nome Sócio</label>
								<input type="text" class="form-control" name="nomeSocio"/>
							</div>
							<div class="form-group">
								<label>CPF</label>
								<input type="text" class="form-control cpf" name="cpf"/>
							</div>
							<div class="form-group">
								<input type="submit" class="btn btn-lg btn-primary pull-right margin-0-6" value="Buscar"/>
								<input type="reset" value="Limpar" class="btn btn-default btn-lg pull-right margin-0-6"/>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>	
</body>
</html>