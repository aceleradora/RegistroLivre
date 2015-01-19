/**
 * 
 */
var mensagemFadeOut = function() {
		
	$('#close').click(function(){
		
		$('.alert').fadeOut();
	});
	
}

var botaMascaraCnpj = function() {
	$('#cnpj').mask('99.999.999/9999-99');
}

var botaMascaraCpf = function() {
	$('.cpf').mask('999.999.999-99');
}

$(document).ready(function() {	
	mensagemFadeOut();	
	botaMascaraCnpj();	
	botaMascaraCpf();	
});