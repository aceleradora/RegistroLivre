$(document).ready(function() {
	
	function validarCNPJTempoReal(cnpj) {
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