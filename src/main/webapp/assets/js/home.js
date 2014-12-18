$(document).ready(function() {
	var tagRegistroLivre = $("#registrolivre")

	if (tagRegistroLivre) {
		$("#inputaBuscaNavbar").hide();
	}
	var contadorTeclas = 0;
	$('#campoPesquisado').keyup(function(e) {
		var busca = $('#campoPesquisado').val();
		contadorTeclas++;
		if (contadorTeclas == 2) {
			procura(busca);
		} else if ((contadorTeclas - 2) % 3 == 0) {
			procura(busca);
		}
	});
});

function procura(busca) {
	$.ajax({
		url : "/empresa/autoCompletar",
		type : "GET",
		data : {
			"textoDigitado" : busca
		}
	}).done(function(data) {
		$("#campoPesquisado").autocomplete({
			source : data
		});
	});
}
