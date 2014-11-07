<html>
<head>
	<link href="assets/css/bootstrap.css" rel="stylesheet">
</head>
	<body>
		<div class="container">
			<div class="row">
				<div class="col-lg-6 col-lg-offset-3">
					<div class="panel panel-default">
						
						<div class="panel-heading">
							<h1>Cadastrar Empresa</h1>
						</div>
					
						
						<form class="form" action="" method="POST">
							
							<div class="panel-body">
						
							<div class="form-group">
								<label class="control-label">CNPJ</label>
								<input class="form-control" type="text" name="cnpj" value=""/>
							</div>
							<div class="form-group">
								<label class="control-label">Nome fantasia:</label>
								<input class="form-control" type="text" name="nomeFantasia" value=""/>
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
								<select name="estado">
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
							<input type="submit" class="btn btn-primary" value="Enviar"/>
						</form>
						
						</div>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>