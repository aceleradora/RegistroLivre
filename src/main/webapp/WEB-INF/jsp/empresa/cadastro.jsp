<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>Cadastro de Empresa</title>
	<link href="assets/css/bootstrap/css/bootstrap.css" rel="stylesheet">
	<link href="assets/css/main.css" rel="stylesheet">
	<script src="assets/js/visualizacao.js"></script>		
</head>
	<body>
		<div class="container">
			<div class="row">
				<div class="col-lg-6 col-md-6 col-lg-offset-3 col-md-offset-3">
				<c:if test="${mensagem != null}">
	    			<div class="alert alert-error alert-dismissible" role="alert">
					  	<button type="button" class="close" id="close" data-dismiss="alert">
				  			<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
				  		</button>
					  	<strong>${mensagem} Inválido!</strong> 
					</div>
	    		</c:if>
					<div class="panel panel-default margin-35-0">
						
						<div class="panel-heading centralize">
							<h2>Cadastrar Empresa</h2>
						</div>
						
						<form class="form" name="formulario" action="empresa/cadastrar" method="POST" onsubmit="return validarCadastro();" enctype="multipart/form-data">
						
							<ul class="list-group">
								
								<li class="list-group-item">
								
									<div class="list-group-item-heading centralize">
										<h4>Dados da empresa</h4>
									</div>
									
							<div class="form-group has-feedback" id="cnpj-group">
								<label class="control-label">CNPJ <abbr title="Preenchimento obrigatório">*</abbr></label>
								<input class="form-control" type="text" name="empresa.cnpj" id="cnpj" value="" placeholder="ex: 00.000.000/0000-00" required/>
								<span class="glyphicon form-control-feedback"></span>
							</div>
						
							<div class="form-group has-feedback" id="nomeFantasia-group">
								<label class="control-label">Nome fantasia  <abbr title="Preenchimento obrigatório">*</abbr> </label>
								<input class="form-control" type="text" name="empresa.nomeFantasia" id="nomeFantasia" value="" placeholder="ex: Jaffari" required/>
								<span class="glyphicon form-control-feedback"></span>
							</div>
						
							<div class="form-group">
								<label class="control-label">Razão Social</label>
								<input class="form-control" type="text" name="empresa.razaoSocial" placeholder="ex: Cia Jaffari Comércio e Indústria LTDA" value=""/>
							</div>
							
							<div class="row">
								<div class="col-lg-9">
											<div class="form-group">
												<label class="control-label">Endereço</label>
												<input class="form-control" type="text" name="empresa.endereco.logradouro" placeholder="ex: Av. Ipiranga" value=""/>
											</div>
										</div>
										<div class="col-lg-3">
											<div class="form-group">
												<label class="control-label">Número</label>
												<input id="numero" class="form-control" type="text" name="empresa.endereco.numero" value=""/>
											</div>
										</div>
									</div>
								
									<div class="form-group">
										<label class="control-label">Complemento</label>
										<input class="form-control" type="text" name="empresa.endereco.complemento" value="" placeholder="ex: Bloco A - apartamento 720"/>
									</div>
								
									<div class="row">
										<div class="col-lg-9">
											<div class="form-group">
												<label class="control-label">Cidade</label>
												<input class="form-control" type="text" name="empresa.endereco.cidade" value=""/>
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
										<input id="cep" class="form-control" name="empresa.endereco.cep" type="text" value="" placeholder="000000-000"/>
									</div>
								
									<div class="row">
										<div class="col-lg-6 col-md-6">
											<div class="form-group">
												<label class="control-label">Data de abertura</label>
												<input class="form-control" name="empresa.dataDeAbertura" type="date" value="" />
											</div>
										</div>
										<div class="col-lg-6 col-md-6">
											<div class="form-group">
												<label class="control-label">Emissão de documento</label>
												<input class="form-control" name="empresa.emissaoDoDocumento" type="date" value=""/>
											</div>
										</div>

																	
									</div>
									
									<div class="form-group">
										<label class="control-label">Upload de arquivo:</label>
										<input id="file" class="form-group" type="file" name="file" required/>
									</div>		
									
								
								</li> <!-- list-group-item  -->

								<div id="divSocios">
									<li class="list-group-item" id="socio0">
									
									<div class="list-group-item-heading centralize">
										<h4>Dados dos sócios</h4>
									</div>
								
									<div class="form-group">
										<label class="control-label">Nome do sócio</label>
										<input class="form-control" name="empresa.socios[0].nome" id="nome-socio">
									</div>
									
									<div class="form-group has-feedback" id="cpf-group">
										<label class="control-label">CPF </label>
										<input class="form-control cpf" type="text" name="empresa.socios[0].cpf" id="cpf" placeholder="ex: 000.000.000-00"/>
										<span class="glyphicon form-control-feedback"></span>
									</div>
										
										<div class="form-group">
											<label>
												<input type="checkbox" name="empresa.socios[0].inativo" > Inativo
											</label>
										</div>
									</li> <!-- list-group-li  -->
								</div>
								
								<li class="list-group-item">
									<div class="form-group">
										
										<button type="button" class="btn btn-success pull-right margin-0-6" onclick="adicionaSocio()"><span class="glyphicon glyphicon-plus-sign"></span> Adicionar Sócio</button>
										<br>
									</div>								
								</li>

							</ul> <!-- list-group -->
						
							
							<div class="panel-footer">
									<input type="submit" class="btn btn-lg btn-primary pull-right margin-0-6" value="Enviar" onclick="return validarCadastro();"/>
									<input type="reset" value="Limpar" class="btn btn-default btn-lg pull-right margin-0-6"/>
								<div style="clear:both"></div>
							</div>			
						</form>
					</div> <!-- panel -->
				</div> <!-- col -->
			</div> <!-- row -->
		</div> <!-- container -->
		
	<script src="http://code.jquery.com/jquery-2.1.1.min.js"></script>
	<script src="assets/js/jquery.mask.min.js"></script>
		
	<script src="assets/js/validacao-logica-cadastro.js"></script>
	<script src="assets/js/adiciona-socios.js"></script>	
	<script src="assets/js/validacao-visual-cadastro.js"></script>
		
	</body>
</html>