$(document).ready(function() {
	
	var validarCNPJTempoReal= function (cnpj) {
		if (validarCNPJ(cnpj)) {
			$("#cnpj-group").addClass("has-success has-feedback");
			$("#cnpj-group").removeClass("has-error");

			$("#cnpj-group span").addClass("glyphicon-ok");
			$("#cnpj-group span").removeClass("glyphicon-remove");

		} else {
			$("#cnpj-group").addClass("has-error");
			$("#cnpj-group").removeClass("has-success");

			$("#cnpj-group span").removeClass("glyphicon-ok");
			$("#cnpj-group span").addClass("glyphicon-remove");
		}
	}
		
	$('#cnpj').mask('00.000.000/0000-00', {
		onKeyPress : function() {
			validarCNPJTempoReal($('#cnpj').val());
		}
	});	
	
	$('.cpf').mask('000.000.000-00');
	
	$('.cpf').focusout(function(){		
		if(validarCpf($('.cpf').val())){
			$('#cpf-group').addClass('has-success');
			$('#cpf-group').removeClass('has-error');
			
			$('#cpf-group span').addClass('glyphicon-ok');
			$('#cpf-group span').removeClass('glyphicon-remove');
		}else{			
			$('#cpf-group').removeClass('has-success');
			$('#cpf-group').addClass('has-error');
			
			$('#cpf-group span').removeClass('glyphicon-ok');
			$('#cpf-group span').addClass('glyphicon-remove');			
		}
	});
	
	$('#cep').mask('00000-000');
	$('#numero').mask('00000000000');
	
	
	
	$('#cnpj').focusout(function() {
		if ($('#cnpj').val() == '') {
			$("#cnpj-group").removeClass("has-success");
			$("#cnpj-group").addClass("has-error");

			$("#cnpj-group span").removeClass("glyphicon-ok");
			$("#cnpj-group span").addClass("glyphicon-remove");
		}
	});
		
	$('#nomeFantasia').keypress(function() {
		
		if ($('#nomeFantasia').val().length > 0) {
			$("#nomeFantasia-group").addClass("has-success has-feedback");
			$("#nomeFantasia-group").removeClass("has-error");

			$("#nomeFantasia-group span").addClass("glyphicon-ok");
			$("#nomeFantasia-group span").removeClass("glyphicon-remove");

		} else {
			$("#nomeFantasia-group").removeClass("has-success");
			$("#nomeFantasia-group").addClass("has-error");

			$("#nomeFantasia-group span").removeClass("glyphicon-ok");
			$("#nomeFantasia-group span").addClass("glyphicon-remove");
		}
	});		

	$('#nomeFantasia').focusout(function() {
		$('#nomeFantasia').val($.trim($('#nomeFantasia').val()));
		
		if ($('#nomeFantasia').val() == '') {
			$("#nomeFantasia-group").removeClass("has-success");
			$("#nomeFantasia-group").addClass("has-error");

			$("#nomeFantasia-group span").removeClass("glyphicon-ok");
			$("#nomeFantasia-group span").addClass("glyphicon-remove");
		}
	});	
});