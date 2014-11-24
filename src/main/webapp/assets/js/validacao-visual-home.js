
var colocarMascaraCnpj = function(){
		$('.radio').change(function(){
			if ($("#radioCnpj").is(":checked")){
				$("#campoPesquisado").mask('00.000.000/0000-00');	
			}
			else{
				$("#campoPesquisado").unmask()
			}
			
		});
		}
		
$(document).ready(function() {	
	colocarMascaraCnpj();
	
});
