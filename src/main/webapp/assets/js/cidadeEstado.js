var RegistroLivre = RegistroLivre || {};

RegistroLivre.CidadeEstado = function CidadeEstado(){
	var cria = function cria() {
		new dgCidadesEstados({
			estado: document.getElementById('estado'),
			cidade: document.getElementById('cidade'),
			
			change: true
		});
	};
	
	return {
		cria: cria
	}
};

var cidadeEstado = new RegistroLivre.CidadeEstado();

$(document).ready(function() {
	cidadeEstado.cria();
});