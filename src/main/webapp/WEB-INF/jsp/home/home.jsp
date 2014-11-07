<html>
<head>
	<link href="assets/css/bootstrap.css" rel="stylesheet">
</head>
	<body>
		<h1>Cadastrar Empresa</h1>
		
		<form class="form" action="" method="POST">
			<div class="form-control">
				<label class="control-label">CNPJ</label>
				<input class="form-control" type="text" name="cnpj" value=""/>
			</div>
			
			<label>Nome fantasia:</label>
			<input type="text" name="nome_fantasia" value=""/>
			
			<label>Razão Social:</label>
			<input type="text" name="razao_social" value=""/>
			
			<label>Endereço:</label>
			<input type="text" name="endereco" value=""/>
			
			<label>Número:</label>
			<input type="text" name="numero" value=""/>
			
			<label>Complemento:</label>
			<input type="text" name="complemento" value=""/>
			
			<label>Cidade:</label>
			<input type="text" name="cidade" value=""/>
			
			<label>Estado:</label>			
			<select name="estado">
				<option value="">RS</option>
				<option value="">SP</option>
			</select>
			
			<label>CEP:</label>
			<input type="text" value=""/>
			
			<label>Data de abertura:</label>
			<input name="data_de_abertura" type="date" value=""/>
			
			<label>Emissão de documento:</label>
			<input name="emissao_do_documento" type="date" value=""/>
			
		</form>
	</body>
</html>