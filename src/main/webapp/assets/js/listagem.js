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
		}
	});
}
