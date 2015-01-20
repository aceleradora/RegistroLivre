$(document).ready(function() {
	retiraCampoBuscaNavbar();
	eventoDesbloquearBotaoPesquisar();
	rolaLinksAncoraDeFormaLenta();
	validarBuscaAvancada();
});

function retiraCampoBuscaNavbar(){
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

function eventoDesbloquearBotaoPesquisar(){
	$("#campoPesquisado").keyup(function() {
		var busca = $("#campoPesquisado").val();
		if (busca.length >= 2) {                  
			habilita($("#btn-submit"));
		} else {                                   
			desabilita($("#btn-submit"));
		}
	});
}


function rolaLinksAncoraDeFormaLenta() {
  $('a[href*=#]:not([href=#])').click(function() {
    if (location.pathname.replace(/^\//,'') == this.pathname.replace(/^\//,'') && location.hostname == this.hostname) {
      var target = $(this.hash);
      target = target.length ? target : $('[name=' + this.hash.slice(1) +']');
      if (target.length) {
        $('html,body').animate({
          scrollTop: target.offset().top
        }, 800);
        return false;
      }
    }
  });
}

function contaCamposPreenchidos(formulario){
	var contador = 0;	
	formulario.find("input[type='text']").each(function(){
		if($(this).val().length != 0 && $(this).val() != ' '){
			contador++;
		}
	});	
	return contador;	
}

function habilita(botao){
	botao.prop("disabled", false);
}

function desabilita(botao){
	botao.prop("disabled", true);
}

function validarBuscaAvancada() {
	var formulario = $("#pesquisa-avancada");
	var camposDeTexto = formulario.find("input[type='text']");
	var estados = $("#estado");	
	var estadoSelecionado = estados.find(":selected");
	var botao = $("#botao-pesquisa-avancada");
		
	camposDeTexto.each(function() {
		$(this).keyup(function() {
			estadoSelecionado = $("#estado").find(":selected");			
			if($(this).val().length == 0){
				if(contaCamposPreenchidos($("#pesquisa-avancada")) == 0 && estadoSelecionado.val() == ''){
					desabilita(botao);
				}else{
					habilita(botao);
				}				
			}else{				
				habilita(botao);
			}			
		});
	});
	
	estados.change(function(){
		console.log("Change: " + $(this).find(":selected").val());
		if(contaCamposPreenchidos(formulario) == 0 && $(this).find(":selected").val() == ''){
			desabilita(botao);
		}else{
			habilita(botao);
		}				
	});
}