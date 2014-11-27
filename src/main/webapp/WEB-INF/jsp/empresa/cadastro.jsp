<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta charset="UTF-8" />
	<title>Cadastro de Empresa</title>
	<link href="/assets/css/bootstrap/css/bootstrap.css" rel="stylesheet">
	<link href="/assets/css/main.css" rel="stylesheet">
	<script src="http://code.jquery.com/jquery-2.1.1.min.js"></script>
	<script src="/assets/js/adiciona-socios.js"></script>
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
				<ul class="nav navbar-nav btn-borders-navbar">
					<li><a href="cadastro">Cadastrar Empresa</a></li>
					<li><a href="visualizacao/0">Visualizar Empresa</a></li>
					<li><a href="listagem">Listar Empresas</a></li>
				</ul>
			</div>
		</div>
		</nav>	
	
		<div class="container">
			<div class="row">
				<div class="col-lg-6 col-md-6 col-lg-offset-3 col-md-offset-3">
				
				<c:forEach items="${errors}" var="error">
	    			<div class="alert alert-danger alert-dismissible" role="alert">
  						${error.message}
					</div>
				</c:forEach>
				
				<c:forEach items="${listaErros}" var="erro">
	    			<div class="alert alert-danger alert-dismissible" role="alert">
  						${erro}
					</div>
				</c:forEach>
	    		
					<div class="panel panel-default margin-35-0">
						
						<div class="panel-heading centralize">
							<c:choose>
								<c:when test="${editar != null}">
									<h2>Editar Empresa</h2>
								</c:when>
								<c:otherwise>
									<h2>Cadastrar Empresa</h2>
								</c:otherwise>
							</c:choose>										
						</div>
						

						<form class="form" name="formulario" action="/empresa/cadastrar/${empresa.id}" method="POST" onsubmit="return validarCadastro('${editar}');" enctype="multipart/form-data">

							<div class="panel-body">
								
								<div class="list-group-item-heading centralize">
									<h4>Dados da empresa</h4>
								</div>
										
								<div class="form-group has-feedback" id="cnpj-group">
									<label class="control-label">CNPJ <abbr title="Preenchimento obrigatório">*</abbr></label>
									<input class="form-control" type="text" name="empresa.cnpj" id="cnpj" value="${empresa.cnpj}" placeholder="ex: 00.000.000/0000-00" required/>
									<span class="glyphicon form-control-feedback"></span>
								</div>
							
								<div class="form-group has-feedback" id="nomeFantasia-group">
									<label class="control-label">Nome fantasia  <abbr title="Preenchimento obrigatório">*</abbr> </label>
									<input class="form-control" type="text" name="empresa.nomeFantasia" id="nomeFantasia" value="${empresa.nomeFantasia}" placeholder="ex: Jaffari" required/>
									<span class="glyphicon form-control-feedback"></span>
								</div>
							
								<div class="form-group">
									<label class="control-label">Razão Social</label>
									<input class="form-control" type="text" name="empresa.razaoSocial"placeholder="ex: Cia Jaffari Comércio e Indústria LTDA" value="${empresa.razaoSocial}"/>
								</div>
							
								<div class="row">
									<div class="col-lg-9">
										<div class="form-group">
											<label class="control-label">Endereço</label>
											<input class="form-control" type="text" name="empresa.endereco.logradouro" placeholder="ex: Av. Ipiranga" value="${empresa.endereco.logradouro}"/>
										</div>
									</div>
									<div class="col-lg-3">
										<div class="form-group">
											<label class="control-label">Número</label>
											<input id="numero" class="form-control" type="text" name="empresa.endereco.numero" value="${empresa.endereco.numero}"/>
										</div>
									</div>
								</div>
								
								<div class="form-group">
									<label class="control-label">Complemento</label>
									<input class="form-control" type="text" name="empresa.endereco.complemento" value="${empresa.endereco.complemento}" placeholder="ex: Bloco A - apartamento 720"/>
								</div>
								
								<div class="row">
									<div class="col-lg-9">
										<div class="form-group">
											<label class="control-label">Cidade</label>
											<input class="form-control" type="text" name="empresa.endereco.cidade" value="${empresa.endereco.cidade}"/>
										</div>
									</div>
									<div class="col-lg-3">
										<div class="form-group">
											<label class="control-label">Estado</label>			
											<select name="empresa.endereco.uf" class="form-control">
												<option value="RS">RS</option>
												<option value="SP">SP</option>
											</select>
										</div>
									</div>
								</div>
								
								<div class="form-group">
									<label class="control-label">CEP</label>
									<input id="cep" class="form-control" name="empresa.endereco.cep" type="text" value="${empresa.endereco.cep}" placeholder="000000-000"/>
								</div>
								
								<div class="row">
									<div class="col-lg-6 col-md-6">
										<div class="form-group">
											<label class="control-label">Data de abertura</label>
											<input class="form-control date" name="empresa.dataCriacao" type="text" value="${empresa.dataCriacao}" />
										</div>
									</div>
									<div class="col-lg-6 col-md-6">
										<div class="form-group">
											<label class="control-label">Emissão de documento</label>
											<input class="form-control date" name="empresa.dataEmissaoDocumento" id="dataEmissao" type="text" value="${empresa.dataEmissaoDocumento}"/>
										</div>
									</div>

																
								</div>
									
								<div class="form-group">
									<label class="control-label">Upload de arquivo:</label>
									<c:choose>
										<c:when test="${editar != null}">
											<input name="empresa.url" type="text" value="${empresa.url}" hidden/>
											<br />
											<a class="form-group" href="${empresa.url}">Antigo Arquivo</a>
											<input id="file" class="form-group" type="file" name="arquivo"/>
										</c:when>
										<c:otherwise>
											<input id="file" class="form-group" type="file" name="arquivo" required/>
										</c:otherwise>
									</c:choose>										
									<span class="msg-alert color-red" id="file-alert">Tamanho máximo do arquivo: 5MB.</span>
								</div>		
								

								<div id="divSocios">
									<c:forEach items="${empresa.socios}" var="socio">
										<script> document.onLoad(adicionaSociosCadastrados("${socio.nome}", "${socio.cpf}", "${socio.ativo}")); </script>
									</c:forEach>								
								</div>
								
								
								<div class="form-group">
									
									<button type="button" class="btn btn-success pull-right margin-0-6" onclick="adicionaSocio()"><span class="glyphicon glyphicon-plus-sign"></span> Adicionar Sócio</button>
									<br>
								</div>								
								

							
							</div> <!-- panel-body -->
							
							<div class="panel-footer">									
								<span class="pull-left msg-alert color-red margin-15-0" id="form-alert">Preencha os campos corretamente para enviar.</span>
								<input type="submit" id="btn-submit" class="btn btn-lg btn-primary pull-right margin-0-6" value="Enviar"/>
								<input type="reset" value="Limpar" class="btn btn-default btn-lg pull-right margin-0-6"/>
								<div style="clear:both"></div>
							</div>	
									
						</form>
							
						
					</div> <!-- panel -->
				</div> <!-- col -->
			</div> <!-- row -->
		</div> <!-- container -->
		
	<script src="/assets/js/jquery.mask.min.js"></script>		
	<script src="/assets/js/validacao-logica-cadastro.js"></script>		
	<script src="/assets/js/validacao-visual-cadastro.js"></script>
		
	</body>
</html>