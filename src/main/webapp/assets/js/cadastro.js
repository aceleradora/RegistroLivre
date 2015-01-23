var valorInicialCNPJ;
var valorInicialNomeFantasia;
var salvando = false;

var valorInicialDosCampos = function(){
	valorInicialCNPJ = $("#cnpj").val();
	valorInicialNomeFantasia = $("#nomeFantasia").val();
};

var temCampoMudado = function(){	
	var valorCNPJ = $("#cnpj").val().replace(/[^\d]+/g, '');
	var valorNomeFantasia = $("#nomeFantasia").val();
	
	if(valorCNPJ != valorInicialCNPJ)
		return true;

	if(valorNomeFantasia != valorInicialNomeFantasia)
		return true;
	
	return false;
};

var mensagemFadeOut = function() {		
	$('.close').click(function(){
		$(this).parents('.alert').fadeOut();
	});
}

var associarEventos= function(){
	
	$("#btn-submit").on("click", function(){
		salvando=true;
	});
	
	$("#adiciona-socios").on("click", function(){
		socios.adiciona();
		animacoes.rolaLinksAncoraDeFormaLenta($(".socio-group").last());
	});
	
	$(window).on('beforeunload ',function() {		
		
		if(temCampoMudado() && salvando==false)
			return 'Existem campos preenchidos no formulário.';
	});
}


$(document).ready(function() {	
	valorInicialDosCampos();		
	associarEventos();
	mensagemFadeOut();
	
	$(":file").filestyle({buttonText: "Escolher arquivo"});	
});