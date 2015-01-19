var RegistroLivre = RegistroLivre || {};

RegistroLivre.AutoCompletar = function AutoCompletar(){
	var cria = function cria(){
		$("#campoPesquisado").autocomplete({
			minLength: 2,
			source: function(request, response) {
				$.ajax({
					dataType: "json",
					url: "/empresa/autoCompletar",
					type: "GET",
		            data : {
		    			"textoDigitado" : request.term
		    		},
				}).done(function(dados){
					console.log(dados.list)
					var matcher = new RegExp($.ui.autocomplete.escapeRegex(request.term), "i");
		    	    response($.grep(dados.list, function(value) {
		    	    	value = value.label || value.value || value;
		    	        return matcher.test(value) || matcher.test(normaliza(value));		          
		    	    }));
				});
		    },
			select: function (event, ui) {
			    var category = ui.item.value.split(' ').join('+');
			    var url = "/busca?busca=" + category;
	
			    event.preventDefault();
			    window.location.href = url;
			}
		});
	}
	
	return{
		cria: cria
	}
}

var autocompletar = new RegistroLivre.AutoCompletar();

$(document).ready(function() {
	autocompletar.cria();
});
