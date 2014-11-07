<html>
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
							<h1>Cadastrar Empresa</h1>
						</div>
					
						
						<div class="panel-body">
							<form class="form" action="" method="POST">
							
								<div class="form-group">
									<label class="control-label">CNPJ</label>
									<input class="form-control" type="text" name="cnpj" value=""/>
								</div>
							
								<div class="form-group">
									<label class="control-label">Nome fantasia:</label>
									<input class="form-control" type="text" name="nomeFantasia" value="" placeholder=""/>
								</div>
							
								<div class="form-group">
									<label class="control-label">Razão Social:</label>
									<input class="form-control" type="text" name="razaoSocial" value=""/>
								</div>
							
								<div class="form-group">
									<label class="control-label">Endereço:</label>
									<input class="form-control" type="text" name="endereco" value=""/>
								</div>
							
								<div class="form-group">
									<label class="control-label">Número:</label>
									<input class="form-control" type="text" name="numero" value=""/>
								</div>
							
								<div class="form-group">
									<label class="control-label">Complemento:</label>
									<input class="form-control" type="text" name="complemento" value=""/>
								</div>
							
								<div class="form-group">
									<label class="control-label">Cidade:</label>
									<input class="form-control" type="text" name="cidade" value=""/>
								</div>
							
								<div class="form-group">
									<label class="control-label">Estado:</label>			
									<select name="estado" class="form-control">
										<option value="">RS</option>
										<option value="">SP</option>
									</select>
								</div>
							
								<div class="form-group">
									<label class="control-label">CEP:</label>
									<input class="form-control" type="text" value=""/>
								</div>
							
								<div class="form-group">
									<label class="control-label">Data de abertura:</label>
									<input class="form-control" name="dataDeAbertura" type="date" value=""/>
								</div>
							
								<div class="form-group">
									<label class="control-label">Emissão de documento:</label>
									<input class="form-control" name="emissaoDoDocumento" type="date" value=""/>
								</div>

							</div>
							
							<div class="panel-footer">
									<input type="submit" class="btn btn-lg btn-primary pull-right margin-0-6" value="Enviar"/>
									<input type="reset" value="Limpar" class="btn btn-default btn-lg pull-right margin-0-6"/>
								<div style="clear:both">
							</div>
						
						</form>
						

					</div> <!-- panel -->
				</div> <!-- col -->
			</div> <!-- row -->
		</div> <!-- container -->


	</body>
</html>