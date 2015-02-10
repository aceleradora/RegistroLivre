var RegistroLivre = RegistroLivre || {};
var tabelaGlobal;

RegistroLivre.DataTable = function DataTable(){ 
	function cria(dados){
		var dataEmissaoOrdenada = 4;
		var dataRegistro = 5;
		var id = 6;
		
		var tabela = $('#tabelaListagem').DataTable({
			data : dados,
			responsive: true,
			stateSave: true,
			
			columns : [ {},
			            { data : 'nomeFantasia'	}, 
			            { data : 'endereco.logradouro', className:"desktop"},
			            { data : 'dataEmissaoDocumento', className:"desktop"},
	 		            { data : 'dataEmissaoOrdenada', className:"never"},
			            { data : 'dataRegistro', className:"never"},
			            { data : 'id', className:"never"}
			          ],		
	        "aoColumnDefs" : [ {
				"iDataSort" : 3,
				"aTargets" : [2]
			},
			{ 
				'orderable': false,
				'aTargets': [0] 
			},
			{
				"render" : function(data, type, row){
					return '<input type="checkbox" class="datatable-selecao">'; 
				},
				"targets" : 0 
			},{
				"aTargets" : [dataEmissaoOrdenada],
				"visible" : false,
			} ,{
				"aTargets" : [dataRegistro],
				"visible" : false,
			} ,{
				"aTargets" : [id],
				"visible" : false,
			}],
			"order": [[ dataRegistro, "desc" ]],
			"rowCallback": function(row, data){
				$("td:gt(0)", row).on('click', function(){
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
	        },
	        "autoWidth": false
		});
		
		criaBotaoDownloadMultiplo();
		criaMultiselecao();
		tabelaGlobal = tabela;
	};

	var criaBotaoDownloadMultiplo = function criaBotaoDownloadMultiplo(){
		var $botaoDownload = $('<button id="btn-multi-download" style="display: none; margin-left:60px" class="btn btn-success">Download</button>');
		$("#tabelaListagem_length").append($botaoDownload);
		eventoBotaoMultiDownload();
	} 
	
	var criaMultiselecao = function criaMultiselecao(){
		$('.datatable-selecao').click(function(){
			$(this).parents('tr').toggleClass('selected');
			var $botaoDownload = $('#btn-multi-download');

			if(tabelaGlobal.rows('.selected')[0].length === 0){
				$botaoDownload.hide();
			}else{
				$botaoDownload.show();
			}
		});
	};
	
	var eventoBotaoMultiDownload = function eventoBotaoMultiDownload(){
		$('#btn-multi-download').click(function(){
			var nomeEmpresas = "Você está baixando o arquivo das empresas: ";			
			var get = '/empresa/download?';
			tabelaGlobal.rows('.selected').data().each(function(data){
				get += 'ids=' + data.id + '&'; 
				nomeEmpresas += data.nomeFantasia + ", ";
			});
			
			nomeEmpresas = nomeEmpresas.substring(0, nomeEmpresas.length-2) + ".";
			
			if(confirm($('<textarea />').html(nomeEmpresas).text())){
				window.location.href = get;
			}
		});
	};
	
	return {
		cria : cria
	}
}

var datatable = new RegistroLivre.DataTable();