<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>Cadastro de Empresa</title>
	<link href="assets/css/bootstrap.css" rel="stylesheet">
	<link href="assets/css/main.css" rel="stylesheet">
</head>
	<body>
		<div class="container">
			<div class="row">
				<div class="col-lg-6 col-md-6 col-lg-offset-3 col-md-offset-3">
					<div class="panel panel-default margin-35-0">
						
						<div class="panel-heading">
							<h2>Cadastrar Empresa</h2>
						</div>
					
						
						
						<form class="form" action="" method="POST">
						
							<div class="panel-body">
						
							<div class="form-group">
								<label class="control-label">CNPJ <abbr title="Preenchimento obrigatório">*</abbr></label>
								<input class="form-control" type="text" name="cnpj" value="" placeholder="ex: 00.000.000/0000-00" required/>
							</div>
						
							<div class="form-group">
								<label class="control-label">Nome fantasia  <abbr title="Preenchimento obrigatório">*</abbr> </label>
								<input class="form-control" type="text" name="nomeFantasia" value="" placeholder="ex: Jaffari"/>
							</div>
						
							<div class="form-group">
								<label class="control-label">Razão Social</label>
								<input class="form-control" type="text" name="razaoSocial" placeholder="ex: Cia Jaffari Comércio e Indústria LTDA" value=""/>
							</div>
							
							<div class="row">
								<div class="col-lg-9">
									<div class="form-group">
										<label class="control-label">Endereço</label>
										<input class="form-control" type="text" name="endereco" placeholder="ex: Av. Ipiranga" value=""/>
									</div>
								</div>
								<div class="col-lg-3">
									<div class="form-group">
										<label class="control-label">Número</label>
										<input class="form-control" type="text" name="numero" value=""/>
									</div>
								</div>
							</div>
						
							<div class="form-group">
								<label class="control-label">Complemento</label>
								<input class="form-control" type="text" name="complemento" value="" placeholder="ex: Bloco A - apartamento 720"/>
							</div>
						
							<div class="row">
								<div class="col-lg-9">
									<div class="form-group">
										<label class="control-label">Cidade</label>
										<input class="form-control" type="text" name="cidade" value=""/>
									</div>
								</div>
								<div class="col-lg-3">
									<div class="form-group">
										<label class="control-label">Estado</label>			
										<select name="estado" class="form-control">
											<option value="">RS</option>
											<option value="">SP</option>
										</select>
									</div>
								</div>
							</div>
						
							<div class="form-group">
								<label class="control-label">CEP</label>
								<input class="form-control" type="text" value="" placeholder="000000-000"/>
							</div>
						
							<div class="row">
								<div class="col-lg-6 col-md-6">
									<div class="form-group">
										<label class="control-label">Data de abertura</label>
										<input class="form-control" name="dataDeAbertura" type="date" value=""/>
									</div>
								</div>
								<div class="col-lg-6 col-md-6">
									<div class="form-group">
										<label class="control-label">Emissão de documento</label>
										<input class="form-control" name="emissaoDoDocumento" type="date" value=""/>
									</div>
								</div>
							</div>

						</div>
						
							<div class="panel-footer">
									<input type="submit" class="btn btn-lg btn-primary pull-right margin-0-6" value="Enviar"/>
									<input type="reset" value="Limpar" class="btn btn-default btn-lg pull-right margin-0-6"/>
								<div style="clear:both"></div>
							</div>
					
						</form>
						

					</div> <!-- panel -->
				</div> <!-- col -->
			</div> <!-- row -->
		</div> <!-- container -->


	</body>
</html>