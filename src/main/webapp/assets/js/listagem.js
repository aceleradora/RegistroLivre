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
            "lengthMenu": "Display _MENU_ records per page",
            "zeroRecords": "Nenhum registro de empresa encontrado.",
            "info": "Mostrando página _PAGE_ de _PAGES_",
            "infoEmpty": "No records available",
        }
	});
}
