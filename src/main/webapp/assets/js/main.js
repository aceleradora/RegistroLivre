$(document).ready(function() {
	var windowWidth = $(window).width();
	var windowHeight = $(window).height();
	
	ajustaNavbarParaMobileAoIniciarPagina(windowWidth);
	ajustaTamanhoDoNavbar();
	escondeOMenuParaMobile();
	ajustaTamanhoDaPaginaConformeConteudo(windowHeight);
	ajustaTamanhoDaPagina();
});


function escondeOMenuParaMobile(){
	$(".navbar-toggle").click(function() {
		if($(".navbar-mobile.in").length){
			$(".navbar-mobile").removeClass("in");
		} 
		else{
			$(".navbar-mobile").addClass("in");
		}
	});
}


function ajustaNavbarParaMobileAoIniciarPagina(windowWidth){
	if(windowWidth > 765){
		$(".navbar-mobile").addClass("in");
	}
	else{
		$(".navbar-mobile").removeClass("in");
	}
}


function ajustaTamanhoDoNavbar(){
	$(window).resize(function(){
		var windowWidth = $(window).width();
		
		ajustaNavbarParaMobileAoIniciarPagina(windowWidth);
	})
}


function ajustaTamanhoDaPagina(){
	$(window).resize(function(){
		var windowHeight = $(window).height();
		
		ajustaTamanhoDaPaginaConformeConteudo(windowHeight);
	})
}


function ajustaTamanhoDaPaginaConformeConteudo(windowHeight){
	
	if($(".rodape").length){
		if($(".rodape").offset().top < windowHeight){
			$(".content").css({"min-height": (windowHeight - $(".rodape").height()) - 130})
		}	
	}		
}