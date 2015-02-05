$(document).ready(function() {
	esconderCollapseNavbar();
});

function esconderCollapseNavbar(){
	
	$(".navbar-toggle").click(function() {
		if($(".navbar-mobile.in").length){
			$(".navbar-mobile").removeClass("in");
		} else{
			$(".navbar-mobile").addClass("in");
		}
	});
	
}
