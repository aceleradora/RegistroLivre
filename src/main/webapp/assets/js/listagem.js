function criaDatatable(dados){
	$('#tabelaListagem').DataTable({
		data : dados,
		columns : [ { data : 'nomeFantasia'	}, 
		            { data : 'endereco.logradouro' },
		            { data : 'dataEmissaoDocumento' }
		          ],
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
            },
        }
	});
}
