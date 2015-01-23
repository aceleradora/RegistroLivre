<%@ page language="java" contentType="text/html; charset=iso-8859-1" pageEncoding="iso-8859-1"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="pt-br">

<head>

	<c:choose>
		<c:when test="${editar != null}">
			<title>Ediçao de Empresa</title>
		</c:when>
		<c:otherwise>
			<title>Cadastro de Empresa</title>
		</c:otherwise>
	</c:choose>

	<jsp:include page="/WEB-INF/jsp/includes/assets.jsp" />	
	<link rel="stylesheet" href="//code.jquery.com/ui/1.11.2/themes/smoothness/jquery-ui.css">
	<script src="http://code.jquery.com/ui/1.11.2/jquery-ui.js"></script>

	<script type="text/javascript" src="http://cidades-estados-js.googlecode.com/files/cidades-estados-1.2-utf8.js"></script>
	<script src="/assets/js/bootstrap/bootstrap-filestyle.js"></script>
	<script src="/assets/js/cadastro.js"></script>
	<script src="/assets/js/socios.js" charset="utf-8"></script>
	<script src="/assets/js/cidadeEstado.js"></script>
	<script src="/assets/js/autocompletarsocio.js"></script>
</head>
<body>
	<jsp:include page="/WEB-INF/jsp/includes/cabecalho.jsp" />
		<div class="container content">
			<div class="row">
				<div class="col-lg-6 col-md-6 col-lg-offset-3 col-md-offset-3">
				
				<c:forEach items="${errors}" var="error">
	    			<div class="alert alert-danger alert-dismissible" role="alert">
  						${error.message}
					</div>
				</c:forEach>
				
				<c:if test="${erro != null}">
					<div class="alert alert-danger alert-dismissible" role="alert">
						${erro}
					</div>
				</c:if>
				
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
									<label class="control-label">CNPJ <abbr class="cor-vermelha" title="Preenchimento obrigatório">*</abbr></label>
									<input class="form-control" type="text" name="empresa.cnpj" id="cnpj" value="${empresa.cnpj}" placeholder="ex: 00.000.000/0000-00" required/>
									<span class="glyphicon form-control-feedback"></span>
								</div>
							
								<div class="form-group has-feedback" id="nomeFantasia-group">
									<label class="control-label">Nome fantasia  <abbr class="cor-vermelha" title="Preenchimento obrigatÃ³rio">*</abbr> </label>
									<input class="form-control" type="text" name="empresa.nomeFantasia" id="nomeFantasia" value="${empresa.nomeFantasia}" placeholder="ex: Larah Instrumentos Musicais" required/>
									<span class="glyphicon form-control-feedback"></span>
								</div>
								
								<div class="form-group">
									<label class="control-label">Upload de arquivo <abbr class="cor-vermelha" title="Preenchimento obrigatÃ³rio">*</abbr></label>
									<c:choose>
										<c:when test="${editar != null}">
											<input name="empresa.url" type="text" value="${empresa.url}" hidden/>
											<br />
											<a class="form-group" href="${empresa.url}" target="_blank"> ${nomeArquivoAntigo}</a>
											<input id="file" class="form-group filestyle" data-buttonText="Escolher arquivo" type="file" name="arquivo"/>
										</c:when>
										<c:otherwise>
											<input id="file" class="form-group filestyle" data-buttonText="Escolher arquivo" type="file" name="arquivo" required/>
										</c:otherwise>
									</c:choose>										
									<span class="msg-alert color-red" id="file-alert">Tamanho máximo do arquivo: 5MB.</span>
								</div>	
							
								<div class="form-group">
									<label class="control-label">Razão Social</label>
									<input class="form-control" type="text" name="empresa.razaoSocial" placeholder="ex: Cia Larah Instrumentos Musicais LTDA" value="${empresa.razaoSocial}"/>
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
									<div class="col-lg-3">
										<div class="form-group">
											<label class="control-label">Estado</label>			
											<input id="estado" class="form-control" name="empresa.endereco.uf" value="${empresa.endereco.uf}" />
										</div>
									</div>
									<div class="col-lg-9">
										<div class="form-group">
											<label class="control-label">Cidade</label>
											<input id="cidade" class="form-control" name="empresa.endereco.cidade" value="${empresa.endereco.cidade}" />
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
											
											<fmt:formatDate value="${empresa.dataCriacao.time}" pattern="dd/MM/yyyy" var="dataCriacaoFormatada" />
											<input class="form-control date" name="empresa.dataCriacao" type="text" placeholder="01/01/1970" value="${dataCriacaoFormatada}" />
										</div>										
									</div>
									<div class="col-lg-6 col-md-6">
										<div class="form-group">
											<label class="control-label">Emissão de documento</label>
											
											<fmt:formatDate value="${empresa.dataEmissaoDocumento.time}" pattern="dd/MM/yyyy" var="dataEmissaoDocumentoFormatada" />
											<input class="form-control date" name="empresa.dataEmissaoDocumento" id="dataEmissao" type="text" placeholder="01/01/1970" value="${dataEmissaoDocumentoFormatada}"/>
										</div>
									</div>

																
								</div>
								
								<div id="divSocios" class="form-group ui-widget">
									<c:forEach items="${empresa.socios}" var="socio">
										<script>socios.adicionaComDados("${socio.nome}", "${socio.cpf}", "${socio.ativo}"); </script>
									</c:forEach>								
								</div>
								
								
								<div class="form-group centralize">
									<button type="button" id="adiciona-socios" class="btn btn-default margin-0-6"><span class="glyphicon glyphicon-plus-sign"></span> Adicionar Sócio</button>
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
		
	<script src="/assets/js/validacao-logica-cadastro.js"></script>		
	<script src="/assets/js/validacao-visual-cadastro.js"></script>
		
	</body>
</html>