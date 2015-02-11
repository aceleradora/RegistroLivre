var RegistroLivre = RegistroLivre || {};

RegistroLivre.AutoCompletar = function AutoCompletar(){
	var criaBusca = function criaBusca(){
		$("#campo-pesquisado").autocomplete({
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
					ajaxDone.call(this, dados, request, response);
				})
		    },
		    select: function (event, ui) {
			    var category = util.normalizar(ui.item.value.split(' ').join('+'));
			    var url = "/busca?busca=" + category;
	
			    event.preventDefault();
			    window.location.href = url;
			}
		});
	};
	
	var criaSocio = function criaSocio(){
		$(".nome-socio").autocomplete({
			minLength: 1,
			source: function(request, response) {
				$.ajax({
					dataType: "json",
					url: "/empresa/autoCompletarSocio",
					type: "GET",
		            data : {
		    			"textoDigitado" : request.term
		    		},
				}).done(function(dados){
					ajaxDone.call(this, dados, request, response);
				})
		    }
		});
	};
	
	var ajaxDone = function ajaxDone(dados, request, response){
		var matcher = new RegExp($.ui.autocomplete.escapeRegex(request.term), "i");
	    response($.grep(dados.list, function(value) {
	    	value = value.label || value.value || value;
	        return matcher.test(value) || matcher.test(util.normalizar(value));		          
	    }));
	};
	
	return{
		criaBusca: criaBusca,
		criaSocio: criaSocio
	}
}

var autocompletar = new RegistroLivre.AutoCompletar();

$(document).ready(function() {
	if($("#campo-pesquisado")){
		autocompletar.criaBusca();
	}
});
