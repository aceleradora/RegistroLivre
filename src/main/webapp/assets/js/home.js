$(document).ready(function() {
	var tagRegistroLivre = $("#registrolivre")

	if (tagRegistroLivre) {
		$("#inputaBuscaNavbar").hide();
	}
	$("#campoPesquisado").autocomplete({
		source: [] 
	});
	
	$('#campoPesquisado').keyup(function() {
		var busca = $('#campoPesquisado').val();
		if (busca.length == 2) {
			procura(busca);
		}
	});	
});

function procura(busca) {	
	$.ajax({
		dataType: "json",
		url : "/empresa/autoCompletar",
		type : "GET",
		data : {
			"textoDigitado" : busca
		}
	}).done(function(dados) {
		$("#campoPesquisado").autocomplete({
			source: function(request, response) {
		        var results = $.ui.autocomplete.filter(dados.list, request.term);
		        response(results.slice(0, 5));
		    },
			select: function (event, ui) {
			    var category = ui.item.value.split(' ').join('+');
			    var url = "/busca?busca=" + category;

			    event.preventDefault();
			    window.location.href = url;
			}
		});
	});
}
