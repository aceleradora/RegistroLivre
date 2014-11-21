
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
			
			$(this).parents('.cpf-group').addClass('has-success');
			$(this).parents('.cpf-group').removeClass('has-error');
			
			$(this).parents('.cpf-group').find('span').addClass('glyphicon-ok');
			$(this).parents('.cpf-group').find('span').removeClass('glyphicon-remove');
			
			if (verificaCPFTodosSocios()){					
				BotaoSubmit.habilitar();
			}
			else
				BotaoSubmit.desabilitar();
		}
		else{	
			BotaoSubmit.desabilitar();
			$(this).parents('.cpf-group').removeClass('has-success');
			$(this).parents('.cpf-group').addClass('has-error');
			
			$(this).parents('.cpf-group').find('span').removeClass('glyphicon-ok');
			$(this).parents('.cpf-group').find('span').addClass('glyphicon-remove');			
		}
	});
}	


var validarNomeSocioTempoReal = function(){
	$('#divSocios').on('focusout', '.cpf', function(){
		if (($(this).parents('.socio-group').find('.nome-socio').val().length <= 0) && ($(this).val().length > 0)){
		
			BotaoSubmit.desabilitar();
			
			$(this).parents('.socio-group').find('.nome-socio').removeClass('has-success');
			$(this).parents('.socio-group').find('.nome-socio').addClass('has-error');
			
			$(this).parents('.socio-group').find('.nome-socio').find('span').removeClass('glyphicon-ok');
			$(this).parents('.socio-group').find('.nome-socio').find('span').addClass('glyphicon-remove');
			
			
		}else{
			
			BotaoSubmit.habilitar();
				
			$(this).parents('.socio-group').find('.nome-socio').removeClass('has-success');
			$(this).parents('.socio-group').find('.nome-socio').addClass('has-error');
				
			$(this).parents('.socio-group').find('.nome-socio').find('span').addClass('glyphicon-ok');
			$(this).parents('.socio-group').find('.nome-socio').find('span').removeClass('glyphicon-remove');
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

var colocarMascaraDatas = function(){
	$('.date').each(function(){
		$(this).mask('00/00/0000');
	});
}


var removeSocio = function(){
	$('#divSocios').on('click', '.close', function(){
		$(this).parents('.list-group-item').remove();
		if(verificaCPFTodosSocios()){
			BotaoSubmit.habilitar();
		}
		else{
			BotaoSubmit.desabilitar();
		}
			
	});	
}

var BotaoSubmit = {
	habilitar: function(){
		$("#btn-submit").prop('disabled', false);
		$(".msg-alert").hide();	
	},
	desabilitar: function(){
		$("#btn-submit").prop('disabled', true);
		$(".msg-alert").show();			
	}
}

var validarTamanhoPdf = function(){
	$('#file').change(function(){
		var arquivo = document.getElementById("file");
	    if (arquivo.files[0].size > 2000000) {
	            BotaoSubmit.desabilitar();
	    } else {
	        BotaoSubmit.habilitar();
	    }
	});
}
	
$(document).ready(function() {	
	
	validarCNPJVazio();
	validarNomeFantasiaVazio();
	colocaMascaraCNPJ();
	colocaMascaraCPF();
	colocaMascaraCEP();
	colocaMascaraNumero();
	colocarMascaraDatas();
	validarCPFTempoReal();
	validarNomeSocioTempoReal();
	removeSocio();
	validarTamanhoPdf();
	
});