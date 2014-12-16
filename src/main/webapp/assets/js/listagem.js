$(document).ready(function() {
	$("#caixaDeSelecaoDecampo").change(function() {

		var tipoValue = $(this).val();
		var ordemValue = $("#caixaDeSelecaoCresc").val();

		window.location.href = "/ordenacao/" + tipoValue + "/" + ordemValue;

	});
});

function criaDatatable(dados){
	$('#tabelaListagem').DataTable({
		data : dados,
		columns : [ { data : 'nomeFantasia'	         }, 
		            { data : 'dataEmissaoDocumento'  },
		            { data : 'endereco.logradouro'			     },
		          ],
		"rowCallback": function(row, data){
			$("td", row).on('click', function(){
				window.location.href = '/visualizacao/' + data.id;
			});
		}
	});
}