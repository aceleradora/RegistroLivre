$(document).ready(function() {
	associarEventos();
	
	retiraCampoBuscaNavbar();
	eventoDesbloquearBotaoPesquisar();
	validarBuscaAvancada();	
	mascararCampos();
});

function mascararCampos(){
	mascaras.mascararCpf($('.cpf'));
	mascaras.mascararCnpj($('.cnpj'));
}

function associarEventos() {
	$("#abrir-busca-avancada").click(mostraBuscaAvancada);
	$("#fecha-busca-avancada").click(fechaBuscaAvancada);
	$("#adiciona-socios-busca-avancada").click(socios.adicionaBuscaAvancada);
	$("#close").click(fechaAlertaNenhumRegistro);
}

var mostraBuscaAvancada = function mostraBuscaAvancada() {
	$("#busca-avancada").fadeIn();
	animacoes.rolaLinksAncoraDeFormaLenta($("#busca-avancada"));
	criaEventoEstado();
}

function criaEventoEstado(){
	var botao = $("#botao-pesquisa-avancada");
		
	$("#estado").change(function() {
		if (contaCamposPreenchidos($("#pesquisa-avancada")) == 0 && $(this).find(":selected").val() == '') {
			alteraDisabled(botao, true);
		} else {
			alteraDisabled(botao, false);
		}
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

var fechaBuscaAvancada = function fechaBuscaAvancada() {
	$("#busca-avancada").fadeOut();
}

function eventoDesbloquearBotaoPesquisar() {
	$("#campo-pesquisado").keyup(function() {
		var busca = $("#campo-pesquisado").val();
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
			contador++;
		}
	});
	return contador;
}

function alteraDisabled(botao, desabilitado) {
	botao.prop("disabled", desabilitado);
}

var testaCamposBuscaAvancada = function testaCamposBuscaAvancada(botao) {
	estadoSelecionado = $("#estado").find(":selected");
	
	if ($(this).val().length == 0) {
		if (contaCamposPreenchidos($("#pesquisa-avancada")) == 0 && estadoSelecionado.val() == '') {
			alteraDisabled(botao, true);
		} else {
			alteraDisabled(botao, false);
		}
	} else {
		alteraDisabled(botao, false);
	}
}

var validarBuscaAvancada = function validarBuscaAvancada() {
	var formulario = $("#pesquisa-avancada");
	var camposDeTexto = formulario.find("input[type='text']");
	var botao = $("#botao-pesquisa-avancada");
	
	camposDeTexto.each(function() {
		$(this).keyup(testaCamposBuscaAvancada.bind(this, botao));
	});

	$("input[name='empresa.socios[].nome']").each(function() {
		$(this).keyup(testaCamposBuscaAvancada.bind(this, botao));
	});
	
	$("input[name='empresa.socios[].cpf']").each(function() {
		$(this).keyup(testaCamposBuscaAvancada.bind(this, botao));
	});
}