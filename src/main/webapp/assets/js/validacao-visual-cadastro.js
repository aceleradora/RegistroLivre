
	
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


var validarCNPJVazio = function(){
	$('#cnpj').focusout(function() {
		if ($('#cnpj').val() == '') {
			$("#cnpj-group").removeClass("has-success");
			$("#cnpj-group").addClass("has-error");

			$("#cnpj-group span").removeClass("glyphicon-ok");
			$("#cnpj-group span").addClass("glyphicon-remove");
		}
	});	
}


var validarNomeFantasiaVazio = function(){
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
}


var validarCPFTempoReal = function(){
	$('#divSocios').on('focusout', '.cpf', function(){		
		if(validarCpf($(this).val())){
			$("#btn-submit").prop('disabled', false);
			$(".msg-alert").hide();
			
			$(this).parents('.cpf-group').addClass('has-success');
			$(this).parents('.cpf-group').removeClass('has-error');
			
			$(this).parents('.cpf-group').find('span').addClass('glyphicon-ok');
			$(this).parents('.cpf-group').find('span').removeClass('glyphicon-remove');
		}else{	
			$("#btn-submit").prop('disabled', true);
			$(".msg-alert").show();
			
			$(this).parents('.cpf-group').removeClass('has-success');
			$(this).parents('.cpf-group').addClass('has-error');
			
			$(this).parents('.cpf-group').find('span').removeClass('glyphicon-ok');
			$(this).parents('.cpf-group').find('span').addClass('glyphicon-remove');			
		}
	});
}	


var colocaMascaraCNPJ = function(){
	$('#cnpj').mask('00.000.000/0000-00', {
		onKeyPress : function() {
			validarCNPJTempoReal($('#cnpj').val());
		}
	});		
}


var colocaMascaraCPF = function(){
	$('.cpf').mask('000.000.000-00');	
}


var colocaMascaraCEP = function(){
	$('#cep').mask('00000-000');	
}


var colocaMascaraNumero = function(){
	$('#numero').mask('00000000000');
}


var removeSocio = function(){
	$('#divSocios').on('click', '.close', function(){
		$(this).parents('.list-group-item').remove();
	});	
}
	
$(document).ready(function() {	
	
	validarCPFTempoReal();
	validarCNPJVazio();
	validarNomeFantasiaVazio();
	colocaMascaraCNPJ();
	colocaMascaraCPF();
	colocaMascaraCEP();
	colocaMascaraNumero();
	removeSocio();
	
	
});