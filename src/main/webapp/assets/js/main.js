$(document).ready(function() {
	var windowWidth = $(window).width();
	
	ajustaNavbarParaMobileAoIniciarPagina(windowWidth);
	ajustaTamanhoDoNavbar();
	esconderCollapseNavbar();
});

function esconderCollapseNavbar(){
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