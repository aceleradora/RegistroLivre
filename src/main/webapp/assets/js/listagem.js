
$(document).ready(function(){
	$("#caixaDeSelecaoDecampo").change(function(){
		
		var tipoValue = $(this).val();
		var ordemValue = $("#caixaDeSelecaoCresc").val();
		
		window.location.href = "/ordenacao/" + tipoValue + "/" + ordemValue ;
		
	})
})