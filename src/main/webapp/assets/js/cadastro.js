var buscaCidadeEstado = function() {
	new dgCidadesEstados({
		estado : document.getElementById('estado'),
		cidade : document.getElementById('cidade'),
		
		change: true

	});
}


$(document).ready(function() {
	
	$(":file").filestyle({buttonText: "Escolher arquivo"});	

	buscaCidadeEstado();
});