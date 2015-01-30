var cnpjGroup = $("#cnpj-group");
var cnpjGroupIcon = $("#cnpj-group span");
var nomeFantasiaGroup = $("#nomeFantasia-group");
var nomeFantasiaIcon = $("#nomeFantasia-group span");
var nomeFantasia = $('#nomeFantasia');

var validarCNPJTempoReal = function(cnpj) {
	if (validarCNPJ(cnpj)) {
		input.validado(cnpjGroup, cnpjGroupIcon);
	} else {
		input.invalidado(cnpjGroup, cnpjGroupIcon);
	}
}

var validarCNPJVazio = function() {
	$('#cnpj').focusout(function() {
		cnpjDigitado = $('#cnpj').val();
		if (cnpjDigitado == '') {
			input.invalidado(cnpjGroup, cnpjGroupIcon);
		}
		if (cnpjDigitado.length == '00.000.000/0000-00'.length) {
			if (!validarCnpjUnico(cnpjDigitado)) {
				input.invalidado(cnpjGroup, cnpjGroupIcon);
				criaPopoverCNPJ();
				$('#cnpj').popover('show');
			} else {
				validarCNPJTempoReal(cnpjDigitado);
				$('#cnpj').popover('destroy');
			}
		}
	});
}

var criaPopoverCNPJ = function() {
	$('#cnpj').popover({
		animation : "true",
		content : "O CNPJ digitado já está cadastrado!",
		placement : 'bottom'
	})
}

var validarNomeFantasiaVazio = function() {
	nomeFantasia.keypress(function() {

		if (nomeFantasia.val().length > 0) {
			input.validado(nomeFantasiaGroup, nomeFantasiaIcon);
		} else {
			input.invalidado(nomeFantasiaGroup, nomeFantasiaIcon);
		}
	});

	nomeFantasia.focusout(function() {
		nomeFantasia.val($.trim(nomeFantasia.val()));

		if (nomeFantasia.val() == '') {
			input.invalidado(nomeFantasiaGroup, nomeFantasiaIcon);
		}
	});
}

var validarCPFTempoReal = function() {
	$('#divSocios')
			.on(
					'focusout',
					'.cpf',
					function() {
						var cpf = $(this);
						var cpfGroup = cpf.parents('.cpf-group');
						var cpfGroupIcon = cpf.parents('.cpf-group').find(
								'span');
						var nomeSocio = cpf.parents('.socio-group').find(
								'.nome-socio');

						if (validarCpf(cpf.val())
								&& (nomeSocio.val().length > 0)) {
							input.validado(cpfGroup, cpfGroupIcon);

							verificaCPFTodosSocios() ? botaoSubmit.habilitar()
									: botaoSubmit.desabilitar();

						} else {
							botaoSubmit.desabilitar();
							input.invalidado(cpfGroup, cpfGroupIcon);
						}
					});
}

var validarNomeSocioTempoReal = function() {
	$('#divSocios').on('focusout', '.nome-socio', function() {
		var nomeSocio = $(this);
		var nomeSocioIcon = $(this).find('span');
		var cpfSocio = $(this).parents('.socio-group').find('.cpf');

		if ((nomeSocio.length <= 0) && (cpfSocio.length > 0)) {
			botaoSubmit.desabilitar();
			input.invalidado(nomeSocio, nomeSocioIcon);
		} else {
			botaoSubmit.habilitar();
			input.validado(nomeSocio, nomeSocioIcon);
		}
	});
}

var colocaMascaraCNPJ = function() {
	$('#cnpj, #cnpj-busca-avancada').mask('00.000.000/0000-00', {
		onKeyPress : function() {
			validarCNPJTempoReal($('#cnpj').val());
		}
	});
}

var colocaMascaraCPF = function() {
	$('.cpf').mask('000.000.000-00');
}

var colocaMascaraCEP = function() {
	$('#cep').mask('00000-000');
}

var colocaMascaraNumero = function() {
	$('#numero').mask('00000000000');
}

var colocarMascaraDatas = function() {
	$('.date').each(function() {
		$(this).mask('00/00/0000');
	});
}

var removeSocio = function() {
	$('#divSocios').on(
			'click',
			'.close',
			function() {
				$(this).parents('.list-group-item').remove();

				verificaCPFTodosSocios() ? botaoSubmit.habilitar()
						: botaoSubmit.desabilitar();
			});
}

var botaoSubmit = {
	habilitar : function() {
		$("#btn-submit").prop('disabled', false);
		$("#form-alert").hide();
	},
	desabilitar : function() {
		$("#btn-submit").prop('disabled', true);
		$("#form-alert").show();
	}
}

var input = {
	validado : function(input, icon) {
		input.addClass("has-success has-feedback");
		input.removeClass("has-error");

		icon.addClass("glyphicon-ok");
		icon.removeClass("glyphicon-remove");
	},
	invalidado : function(input, icon) {
		input.addClass("has-error");
		input.removeClass("has-success");

		icon.removeClass("glyphicon-ok");
		icon.addClass("glyphicon-remove");
	}
}

var validarTamanhoPdf = function() {
	$('#file').change(function() {
		var arquivo = document.getElementById("file");
		if (arquivo.files[0].size > 5000000) {
			botaoSubmit.desabilitar();
			$('#file-alert').show();
		} else {
			botaoSubmit.habilitar();
			$('#file-alert').hide();
		}
	});
}

$(document).ready(function() {
	validarNomeFantasiaVazio();
	validarCNPJVazio();
	colocaMascaraCNPJ();
	colocaMascaraCPF();
	colocaMascaraCEP();
	colocaMascaraNumero();
	colocarMascaraDatas();
	validarCPFTempoReal();
	validarNomeSocioTempoReal();
	removeSocio();
	validarTamanhoPdf();
});