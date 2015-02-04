var RegistroLivre = RegistroLivre || {};

RegistroLivre.CidadeEstado = function CidadeEstado(){
	var cria = function cria() {
		new dgCidadesEstados({
			estado: document.getElementById('estado'),
			cidade: document.getElementById('cidade'),
			
			change: true
		});
	};
	
	var selecioneEstadoPrimeiro = function selecioneEstadoPrimeiro(){
		$('#cidade').append('<option value="">Selecione o estado primeiro</option>');
	}
	
	return {
		cria: cria,
		selecioneEstadoPrimeiro: selecioneEstadoPrimeiro
	}
};

var cidadeEstado = new RegistroLivre.CidadeEstado();

$(document).ready(function() {
	cidadeEstado.cria();
	cidadeEstado.selecioneEstadoPrimeiro();
});