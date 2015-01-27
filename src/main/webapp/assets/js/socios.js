var RegistroLivre = RegistroLivre || {};

RegistroLivre.Socios = function Socios(){
	var template = 
		'<li class="list-group-item socio-group">' +
			'<div class="list-group-item-heading centralize">' +
				'<h4>Dados do sócio' +
					'<button type="button" class="close" data-dismiss="alert">' +
					    '<span aria-hidden="true">&times;</span>' +
						'<span class="sr-only">Close</span>' +
					 '</button>' +
		         '</h4>' +
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
	
	var templateBuscaAvancada = 
		'<div class="socios-item-busca-avancada">' +
				'<div class="col-lg-8">' +
					'<div class="form-group">' +
						'<label>Nome do sócio</label>' +
						'<input type="text" class="form-control nome-socio" name="empresa.socios[].nome"/>' +
					'</div>' +
				'</div>' +
				'<div class="col-lg-4">' +
					'<div class="form-group">' +
						'<div onclick="socios.removeDaBuscaAvancada(this)" class="pull-right cursor-pointer"><span>&times;</span></div>' +
						'<label>CPF</label>' +
						'<input type="text" class="form-control cpf" name="empresa.socios[].cpf" placeholder="ex: 000.000.000-00"/>' +
					'</div>' +
				'</div>' +
			'</div>';
	
	var adiciona = function adiciona(templateExistente){
		var $template = templateExistente || $(template);
		$template.find('.cpf').mask('000.000.000-00');
		$("#divSocios").append($template);
		var temAutocomplete = $template.find("input[name='empresa.socios[].ativo']").length > 0;
		if(temAutocomplete){
			autocompletarsocio.cria();
		}
	}
		
	var adicionaComDados = function adicionaComDados(nome, cpf, ativo){
		var $template = $(template);
		$template.find("input[name='empresa.socios[].nome']").val(nome);
		$template.find("input[name='empresa.socios[].cpf']").val(cpf);
		$template.find("input[name='empresa.socios[].ativo']").prop('checked', ativo == "true");
		adiciona($template);
	}
	
	var adicionaBuscaAvancada = function adicionaBuscaAvancada(){
		adiciona($(templateBuscaAvancada));
		validarBuscaAvancada();
	}
	
	var removeDaBuscaAvancada = function removeDaBuscaAvancada(botao) {
		$(botao).parents(".socios-item-busca-avancada").remove();
		$("#cnpj-busca-avancada").trigger("keyup");
	}
	
	return {
		adiciona: adiciona,
		adicionaComDados: adicionaComDados,
		adicionaBuscaAvancada: adicionaBuscaAvancada,
		removeDaBuscaAvancada: removeDaBuscaAvancada
	}
}

var socios = new RegistroLivre.Socios();