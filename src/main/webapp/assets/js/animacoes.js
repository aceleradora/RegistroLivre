var RegistroLivre = RegistroLivre || {};

RegistroLivre.Animacoes = function animacoes(){
	
	var rolaLinksAncoraDeFormaLenta = function rolaLinksAncoraDeFormaLenta(elemento){
		$('html,body').animate({
			scrollTop : elemento.offset().top		
		}, 800);
		return false;
	}
	
	
	return{
		rolaLinksAncoraDeFormaLenta : rolaLinksAncoraDeFormaLenta
	}		
}

var animacoes = new RegistroLivre.Animacoes();
