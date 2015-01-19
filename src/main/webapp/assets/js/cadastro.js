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


$(document).ready(function() {	
	valorInicialDosCampos();
	
	$("#btn-submit").on("click", function(){
		salvando=true;
	});
	
	$(window).on('beforeunload ',function() {		
		
		if(temCampoMudado() && salvando==false)
			return 'Existem campos preenchidos no formulário.';
	});	
	
	$(":file").filestyle({buttonText: "Escolher arquivo"});	
});