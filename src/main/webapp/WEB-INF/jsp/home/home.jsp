<%@ page language="java" contentType="text/html; charset=iso-8859-1" pageEncoding="iso-8859-1"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="pt-br">
<head>	
	<jsp:include page="/WEB-INF/jsp/includes/assets.jsp" />
	<title>Registro Livre - A verdade está lá fora!</title>	
	<meta charset="iso-8859-1" />
	<link rel="stylesheet" href="//code.jquery.com/ui/1.11.2/themes/smoothness/jquery-ui.css">
	<script src="http://code.jquery.com/ui/1.11.2/jquery-ui.js"></script>
	
	<% //Manter charset utf-8 para funcionar a acentuação no auto completar %>
	<script src="/assets/js/home.js" charset="utf-8"></script>
	<script src="/assets/js/autocompletar.js" charset="utf-8"></script>
	<script src="/assets/js/socios.js" charset="utf-8"></script>
	<script type="text/javascript" src="http://cidades-estados-js.googlecode.com/files/cidades-estados-1.2-utf8.js"></script>
	<script src="/assets/js/validacao-logica-cadastro.js"></script>		
	<script src="/assets/js/validacao-visual-cadastro.js"></script>
	<script src="/assets/js/cidadeEstado.js"></script>
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
						<span id="error">Não há nenhum registro de empresa para a busca
							efetuada.</span>
					</div>
				</c:if>
				

				<c:if test="${buscaVazia == true}">
					<div class="alert alert-warning alert-dismissible" role="alert">
						<button type="button" class="close" id="close" data-dismiss="alert">
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
						<form class="form" id="form-busca" name="pesquisa" action="/busca" method="GET">
							<div class="form-group ui-widget">
								<input class="form-control" type="text" name="busca" id="campoPesquisado" placeholder="Busque por uma empresa aqui" />
							</div>
							<div class="pull-left padding-30-0-0-0">
								<i class="padding-0-6"><a href="#busca-avancada" id="abrir-busca-avancada">Busca Avançada</a></i>
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
						<h4>Busca Avançada <span id="fecha-busca-avancada" class="pull-right cursor-pointer">&times;</span></h4>
					</div>
					<form class="form" id="pesquisa-avancada" name="pesquisaAvancada" action="buscaAvancada" method="POST" accept-charset="UTF-8,ISO-8859-1" >									
						<div class="panel-body">
							<div class="form-group">
								<label>CNPJ</label>
								<input type="text" id="cnpj-busca-avancada" class="form-control" name="empresa.cnpj" placeholder="ex: 00.000.000/0000-00"/>
							</div>
							<div class="form-group">
								<label>Nome Fantasia</label>
								<input type="text" id="nome-fantasia-busca-avancada" class="form-control" name="empresa.nomeFantasia"/>
							</div>
							<div class="form-group">
								<label>Razão Social</label>
								<input type="text" id="razao-social-busca-avancada" class="form-control" name="empresa.razaoSocial"/>
							</div>
							<div class="row">
								<div class="col-lg-4">
									<div class="form-group">
										<label>Estado</label>
										<input type="text" id="estado" class="form-control" name="empresa.endereco.uf"/>
									</div>
								</div>
								<div class="col-lg-8">
									<div class="form-group">
										<label>Cidade</label>
										<input type="text" id="cidade" class="form-control" name="empresa.endereco.cidade"/>
									</div>
								</div>
							</div>
							<div class="form-group">
								<label>Logradouro</label>
								<input type="text" id="logradouro" class="form-control" name="empresa.endereco.logradouro"/>
							</div>
							<div id="divSocios"class="row">
															
							</div>
							<div class="form-group centralize">
								<button type="button" class="btn btn-default margin-0-6" id="adiciona-socios-busca-avancada"><span class="glyphicon glyphicon-plus-sign"></span> Pesquisar Sócio</button>
								<br>
							</div>	
						</div>
						<div class="panel-footer">
							<input type="submit" id="botao-pesquisa-avancada" value="Buscar" class="btn btn-lg btn-primary pull-right margin-0-6" disabled="true"/>
							<input type="reset" value="Limpar" class="btn btn-default btn-lg pull-right margin-0-6"/>
							<div style="clear:both"></div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	<div class="ajustaRodapeHome">
		<jsp:include page="/WEB-INF/jsp/includes/rodape.jsp" />	
	</div>	
</body>
</html>