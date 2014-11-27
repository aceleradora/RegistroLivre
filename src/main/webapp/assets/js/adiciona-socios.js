var contSocios = 1;

function adicionaSocio(){
	var htmlSocio = 
		'<li class="list-group-item socio-group" id="socio' + contSocios + '">' +
			'<div class="list-group-item-heading centralize">' +
				'<h4>Dados do sócio <button type="button" class="close" data-dismiss="alert"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button></h4>' +
			'</div>' +
			'<div class="form-group">' +
				'<label>Nome do sócio</label>' +
				'<input class="form-control nome-socio" name="empresa.socios[].nome" required>' +
			'</div>' +
			'<div class="form-group has-feedback cpf-group">' +
				'<label>CPF</label>' +
				'<input class="form-control cpf" name="empresa.socios[].cpf" placeholder="ex: 000.000.000-00" required>' +
				'<span class="glyphicon form-control-feedback"></span>' +
			'</div>' +
			'<div class="form-group">' +
				'<label>' + 
					'<input type="checkbox" name="empresa.socios[].ativo" checked> Ativo' +
				'</label>' + 
			'</div>' +
		'</li>';

	$("#divSocios").append(htmlSocio);
	$('.cpf').mask('000.000.000-00');
	contSocios++;
} 

function adicionaSociosCadastrados(nome, cpf, ativo){
	if(ativo){
		botaoHtmlAtivo = '<input type="checkbox" name="empresa.socios[].ativo" checked> Ativo' ;
	} else {
		botaoHtmlAtivo = '<input type="checkbox" name="empresa.socios[].ativo" > Ativo' ;
	}
	var htmlSocioCadastrados = 
		'<li class="list-group-item socio-group" id="socio' + contSocios + '">' +
			'<div class="list-group-item-heading centralize">' +
				'<h4>Dados dos sócios <button type="button" class="close" data-dismiss="alert"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button></h4>' +
			'</div>' +
			'<div class="form-group">' +
				'<label>Nome do sócio</label>' +
				'<input class="form-control nome-socio" name="empresa.socios[].nome" value ="'+nome+'" required>' +
			'</div>' +
			'<div class="form-group has-feedback cpf-group">' +
				'<label>CPF</label>' +
				'<input class="form-control cpf" name="empresa.socios[].cpf" value ="'+cpf+'" placeholder="ex: 000.000.000-00" required>' +
				'<span class="glyphicon form-control-feedback"></span>' +
			'</div>' +
			'<div class="form-group">' +
				'<label>' + 
					botaoHtmlAtivo +
				'</label>' + 
			'</div>' +
		'</li>';

	$("#divSocios").append(htmlSocioCadastrados);
	$('.cpf').mask('000.000.000-00');
	contSocios++;
} 