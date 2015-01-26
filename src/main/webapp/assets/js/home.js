$(document).ready(function() {
	associarEventos();
	
	retiraCampoBuscaNavbar();
	eventoDesbloquearBotaoPesquisar();
	validarBuscaAvancada();	
});

function associarEventos() {
	$("#abrir-busca-avancada").on("click",function(e) {
		e.preventDefault();

		mostraBuscaAvancada();
		animacoes.rolaLinksAncoraDeFormaLenta($("#busca-avancada"));
	});
}

function retiraCampoBuscaNavbar() {
	var tagRegistroLivre = $("#registrolivre");
	if (tagRegistroLivre) {
		$("#inputaBuscaNavbar").hide();
	}
}

function fechaAlertaNenhumRegistro() {
	$(".alert").fadeOut();
}

function mostraBuscaAvancada() {
	$("#busca-avancada").fadeIn();
}

function fechaBuscaAvancada() {
	$("#busca-avancada").fadeOut();
}

function eventoDesbloquearBotaoPesquisar() {
	$("#campoPesquisado").keyup(function() {
		var busca = $("#campoPesquisado").val();
		if (busca.length >= 2) {
			alteraDisabled($("#btn-submit"), false);
		} else {
			alteraDisabled($("#btn-submit"), true);
		}
	});
}

function contaCamposPreenchidos(formulario) {
	var contador = 0;
	formulario.find("input[type='text']").each(function() {
		if ($(this).val().length != 0 && $(this).val() != ' ') {

		}
	});
	return contador;
}

function alteraDisabled(botao, desabilitado) {
	botao.prop("disabled", desabilitado);
}

function validarBuscaAvancada() {
	var formulario = $("#pesquisa-avancada");
	var camposDeTexto = formulario.find("input[type='text']");
	var estados = $("#estado");
	var estadoSelecionado = estados.find(":selected");
	var botao = $("#botao-pesquisa-avancada");

	camposDeTexto
			.each(function() {
				$(this)
						.keyup(
								function() {
									estadoSelecionado = $("#estado").find(
											":selected");
									if ($(this).val().length == 0) {
										if (contaCamposPreenchidos($("#pesquisa-avancada")) == 0
												&& estadoSelecionado.val() == '') {
											alteraDisabled(botao, true);
										} else {
											alteraDisabled(botao, false);
										}
									} else {
										alteraDisabled(botao, false);
									}
								});
			});

	estados.change(function() {
		console.log("Change: " + $(this).find(":selected").val());
		if (contaCamposPreenchidos(formulario) == 0
				&& $(this).find(":selected").val() == '') {
			alteraDisabled(botao, true);
		} else {
			alteraDisabled(botao, false);
		}
	});
}