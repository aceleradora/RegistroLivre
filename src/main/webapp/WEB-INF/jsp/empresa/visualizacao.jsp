<%@ page language="java" contentType="text/html; iso-8859-1" pageEncoding="iso-8859-1"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
	<jsp:include page="/WEB-INF/jsp/includes/assets.jsp" />
	<script src="/assets/js/visualizacao.js"></script>
	<title>Visualizar Empresa</title>
</head>
<body>
	<jsp:include page="/WEB-INF/jsp/includes/cabecalho.jsp" />

	<div class="container content">
		<div class="row">
			<div class="col-lg-6 col-md-8 col-lg-offset-3 col-md-offset-2">
				<c:if test="${mensagem != null}">
	    			<div class="alert alert-success alert-dismissible" role="alert">
					  	<button type="button" class="close" id="close" data-dismiss="alert">
				  			<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
				  		</button>
					  	<strong>${mensagem}</strong> 
					</div>
	    		</c:if>
				<div class="panel panel-default margin-35-0">
					<div class="panel-heading centralize">
						<h2>${empresa.nomeFantasia}</h2>
					</div>
					<div class="panel-body">

								<dl>
									<dt>CNPJ:</dt>
									<span id="cnpj" >${empresa.cnpj}</span>
								</dl>
								<dl>
									<dt>Nome fantasia:</dt> 
									<dd>${empresa.nomeFantasia}</dd>
								</dl>
								<dl>
									<dt>Razão Social:</dt> 
									<dd> ${empresa.razaoSocial}</dd>
								</dl>

						<dl>
							<dt>Endereço:</dt> 
							<dd>${empresa.endereco.logradouro}</dd>
						</dl>
						<div class="row">
							<div class="col-lg-4 col-md-4 col-sm-4 col-xs-12">
								<dl>
									<dt>Número:</dt>
									<dd>${empresa.endereco.numero}</dd>
								</dl>
							</div>
							<div class="col-lg-8 col-md-8 col-sm-8 col-xs-12">
								<dl class="pull-right-lg">
									<dt>Complemento:</dt>
									<dd>${empresa.endereco.complemento}</dd>
								</dl>
							</div>
						</div>
						<div class="row">
							<div class="col-lg-7 col-md-12 col-sm-12 col-xs-12">
								<dl>
									<dt>Cidade:</dt>
									<dd>${empresa.endereco.cidade}</dd>
								</dl>
							</div>
							<div class="col-lg-2 col-md-6 col-sm-6 col-xs-12">
								<dl>
									<dt>UF:</dt> 
									<dd>${empresa.endereco.uf}</dd>
								</dl>
							</div>
							<div class="col-lg-3 col-md-6 col-sm-6 col-xs-12">
								<dl class="pull-right-lg">
									<dt>CEP:</dt>
									<dd>${empresa.endereco.cep}</dd>
								</dl>
							</div>
						</div>
						<div class="row">
							<div class="col-lg-6 col-md-6 col-sm-6 col-xs-12">
								<dl>
									<dt>Data de abertura:</dt>
									<dd>
										<fmt:formatDate value="${empresa.dataCriacao.time}" pattern="dd/MM/yyyy"  />
									</dd>							
								</dl>
							</div>
							<div class="col-lg-6 col-md-6 col-sm-6 col-xs-12">
								<dl class="pull-right-lg">
									<dt>Emissão de documento:</dt>						
									<dd>
										<fmt:formatDate value="${empresa.dataEmissaoDocumento.time}" pattern="dd/MM/yyyy"  />
									</dd>
								</dl>					
							</div>
						</div>
						
						<a class="btn btn-default btn-download btn-download-pull-left" href="${empresa.url}" download>
							<img style="width:12%; float:left;" src="../assets/img/PDF_icon.png" />
							<label>Download de PDF</label>
						</a>
						
						
						<h3 class="centralize panel-divider padding-6-0 margin-30-0">Estrutura Societária</h3>
					

						<c:choose>
						
							<c:when test="${empresa.socios.size() == 0}">
								<div class="centralize">Sem sócios cadastrados.</div>
							</c:when>
							
							<c:otherwise>
								<c:forEach items="${empresa.socios}" var="socio">
									<div class="socio">
										<dl>
											<dt>Nome: </dt>
											<dd>${socio.nome}</dd>
										</dl>
										<dl>
											<dt>CPF: </dt>
											<dd class="cpf">${socio.cpf}</dd>								
										</dl>
										
										<c:if test="${socio.ativo == true}">
											<span class="label label-success">Ativo</span>
										</c:if>
										<c:if test="${socio.ativo == false}">
											<span class="label label-danger">Inativo</span>
										</c:if>
									</div>
								</c:forEach>
							</c:otherwise>
							
						</c:choose>

					</div>

					<div class="panel-footer">
						<a class="btn btn-default" href="javascript:history.back(1)">Voltar</a>
						<a href="/atualizar/${empresa.id}" id="btn-submit" class="btn btn-primary pull-right margin-0-6" >Editar</a>
						<div style="clear: both"></div>
					</div>
					<!-- panel -->
				</div>
				<!-- col -->
			</div>
			<!-- row -->
		</div>
		</div>
		<!-- container -->
		<jsp:include page="/WEB-INF/jsp/includes/rodape.jsp" />		
</body>
</html>