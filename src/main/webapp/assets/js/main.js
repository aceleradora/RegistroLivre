$(document).ready(function(){
	 $('#cnpj').mask('00.000.000/0000-00',
	 { 
		onKeyPress: function(){
			validaCNPJTempoReal($('#cnpj').val());
		}
	});
});