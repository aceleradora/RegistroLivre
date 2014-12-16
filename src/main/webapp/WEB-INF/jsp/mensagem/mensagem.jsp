<%@ page language="java" contentType="text/html; charset=iso-8859-1"
    pageEncoding="iso-8859-1"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="pt-br">
<head>
	<meta charset="iso-8859-1" />
	<title>Contato</title>
	<link href="/assets/css/bootstrap/css/bootstrap.css" rel="stylesheet">
	<link href="/assets/css/main.css" rel="stylesheet">
	<script src="http://code.jquery.com/jquery-2.1.1.min.js"></script>
	<script src="/assets/js/adiciona-socios.js" charset="utf-8"></script>
</head>
	<body>
	<jsp:include page="/WEB-INF/jsp/includes/cabecalho.jsp" />
		<div class="container">
			<div class="row">
				<div class="col-lg-6 col-md-6 col-lg-offset-3 col-md-offset-3">
				
				<c:forEach items="${errors}" var="error">
	    			<div class="alert alert-danger alert-dismissible" role="alert">
  						${error.message}
					</div>
				</c:forEach>
				
				<c:if test="${erro} != null">
					<div class="alert alert-danger alert-dismissible" role="alert">
						${erro}
					</div>
				</c:if>
				
				<div class="panel panel-default margin-35-0">
						
						<div class="panel-heading centralize">
									<h2>Contato</h2>									
						</div>
						

						<form class="form" name="formulario" action="mx1.hostinger.com.br" method="POST" onsubmit="return validarCadastro('${editar}');" enctype="multipart/form-data">

							<div class="panel-body">
								
								<div class="list-group-item-heading centralize">
									<input type=hidden name="destino" value="contato@registrolivre.hol.es">
								</div>								
										
								<div class="form-group has-feedback" id="nome-group">
									<label class="control-label">Nome <abbr title="Preenchimento obrigatório">*</abbr></label>
									<input class="form-control" type="text" name="" id="" value="" placeholder="ex: Barbara Souza" required/>
									<span class="glyphicon form-control-feedback"></span>
								</div>
							
								<div class="form-group has-feedback" id="email-group">
									<label class="control-label">E-mail  <abbr title="Preenchimento obrigatório">*</abbr> </label>
									<input class="form-control" type="email" name="" id="" value="" placeholder="ex: barbaras@gmail.com" required/>
									<span class="glyphicon form-control-feedback"></span>
								</div>
							
								<div class="form-group">
									<label class="control-label">Assunto</label>
									<select name="" id="" >
										<option value="##" selected>Escolha uma opção </option>
										<option value="duvida">Dúvida</option>
										<option value="reclamacao">Reclamação</option>										
										<option value="sugestao">Sugestão</option>
									</select>
								</div>
															
								<div class="row">
									<div class="col-lg-9">
										<div class="form-group">
											<label class="control-label">Mensagem  <abbr title="Preenchimento obrigatório">*</abbr> </label> 
											<br>
											<textarea rows="5" cols="40" name="" id="" value="" required></textarea>
										</div>
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