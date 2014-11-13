var contSocios = 1;

function adicionaSocio(){
	var htmlSocio = 
		'<li class="list-group-item" id="socio' + contSocios + '">' +
			'<div class="list-group-item-heading centralize">' +
				'<h4>Dados dos sócios <button type="button" class="close" data-dismiss="alert"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button></h4>' +
			'</div>' +
			'<div class="form-group">' +
				'<label>Nome do sócio</label>' +
				'<input class="form-control" name="empresa.socios[' + contSocios + '].nome">' +
			'</div>' +
			'<div class="form-group has-feedback cpf-group">' +
				'<label>CPF</label>' +
				'<input class="form-control cpf" name="empresa.socios[' + contSocios + '].cpf" placeholder="ex: 000.000.000-00">' +
				'<span class="glyphicon form-control-feedback"></span>'
			'</div>' +
			'<div class="form-group">' +
				'<label>' +
					'<input type="checkbox" name="empresa.socios[' + contSocios + '].inativo"> Inativo'
				'</label>' + 
			'</div>' +
		'</li>';

	$("#divSocios").append(htmlSocio);
	$('.cpf').mask('000.000.000-00');
	contSocios++;
} 