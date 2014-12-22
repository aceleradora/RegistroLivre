function criaDatatable(dados){
	var tabela = $('#tabelaListagem').dataTable({
		data : dados,
		columns : [ { data : 'nomeFantasia'	}, 
		            { data : 'endereco.logradouro' },
		            { data : 'dataEmissaoDocumento' },
 		            { data : 'dataEmissaoOrdenada' },
		            { data : 'dataRegistro'},
		            { data : 'id'}
		          ],
        "aoColumnDefs" : [ {
			"iDataSort" : 3,
			"aTargets" : [2]
			
		},{
			"aTargets" : [3],
			"visible" : false,
		} ,{
			"aTargets" : [4],
			"visible" : false,
		} ,{
			"aTargets" : [5],
			"visible" : false,
		} ],
		"order": [[ 4, "desc" ]],
		"rowCallback": function(row, data){
			$("td", row).on('click', function(){
				window.location.href = '/visualizacao/' + data.id;
			});
		},
		"language": {
            "lengthMenu": "Mostrar _MENU_ resultados por página",
            "zeroRecords": "Nenhum registro de empresa encontrado.",
            "search": "Filtrar resultados:",
            "info": "Mostrando página _PAGE_ de _PAGES_",
            "infoEmpty": "Sem registros disponíveis",
            "paginate": {
                "first":      "Primeiro",
                "last":       "Último",
                "next":       "Próximo",
                "previous":   "Anterior"
            }
        }
	});
}
