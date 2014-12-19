/**
 * 
 */
var mensagemFadeOut = function() {
		
	$('#close').click(function(){
		
		$('.alert').fadeOut();
	});
	
}

$(document).ready(function() {	
	mensagemFadeOut();		
});