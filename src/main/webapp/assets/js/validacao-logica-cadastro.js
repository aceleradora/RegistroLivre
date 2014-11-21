function validarCadastro() {

	if (!validarCNPJ(formulario.cnpj.value)) {
		alert("Preencha o CNPJ corretamente.");
		formulario.cnpj.focus();
		return false;
	}

	if (formulario.nomeFantasia.value == "") {
		alert("Preencha o nome fantasia corretamente.");
		formulario.nomeFantasia.focus();
		return false;
	}

	if (!validarPdf(formulario.file)) {
		alert("Arquivo inexistente ou com a extensão inválida. (Somente PDF's são permitidos.)");
		return false;
	}

	if (!validarCpf(formulario.cpf.value)) {
		alert("Preencha o CPF do socio corretamente!");

		formulario.cpf.focus();
		return false;
	}

}

function validarPdf(objFileControl) {

	var file = objFileControl.value;
	var len = file.length;
	var ext = file.slice(len - 4, len);

	return ext.toUpperCase() == ".PDF";
}

function validarCpf(cpfDigitado) {

	var cpf = cpfDigitado.replace(/[.\-]/g, '');
	
	erro = new String;		

	if (cpf == "00000000000" || cpf == "11111111111" || cpf == "22222222222"
			|| cpf == "33333333333" || cpf == "44444444444"
			|| cpf == "55555555555" || cpf == "66666666666"
			|| cpf == "77777777777" || cpf == "88888888888"
			|| cpf == "99999999999") {

		return false;
	}

	var digitosCpf = cpf.split('');
	var acumuladorDigitos = 0;
	var peso = 11;
	
	var primeiroDigitoVerificador = digitosCpf[9];
	var segundoDigitoVerificador  = digitosCpf[10];
	
	var primeiroDigitoVerificadorCalculado = 0;
	var segundoDigitoVerificadorCalculado  = 0;

	for (var i = 0; i < 9; i++) {				
		acumuladorDigitos += (digitosCpf[i] * --peso);
	}

	var resto = acumuladorDigitos % 11; 
	
	primeiroDigitoVerificadorCalculado = (resto < 2)? 0 : 11 - resto;
		
	acumuladorDigitos = 0;	
	peso = 11;
	
	for (var i = 0; i < 10; i++){
		acumuladorDigitos += (digitosCpf[i] * peso--);
	}

	resto = acumuladorDigitos % 11;
		
	segundoDigitoVerificadorCalculado = (resto < 2)? 0 : 11 - resto;	
	
	return !(primeiroDigitoVerificador != primeiroDigitoVerificadorCalculado ||
			 segundoDigitoVerificador  != segundoDigitoVerificadorCalculado);		
}

function validarCNPJ(cnpj) {

	cnpj = cnpj.replace(/[^\d]+/g, '');

	if (cnpj == '')
		return false;

	if (cnpj.length != 14)
		return false;

	if (cnpj == "00000000000000" || cnpj == "11111111111111"
			|| cnpj == "22222222222222" || cnpj == "33333333333333"
			|| cnpj == "44444444444444" || cnpj == "55555555555555"
			|| cnpj == "66666666666666" || cnpj == "77777777777777"
			|| cnpj == "88888888888888" || cnpj == "99999999999999")
		return false;

	tamanho = cnpj.length - 2
	numeros = cnpj.substring(0, tamanho);
	digitos = cnpj.substring(tamanho);
	soma = 0;
	pos = tamanho - 7;
	for (i = tamanho; i >= 1; i--) {
		soma += numeros.charAt(tamanho - i) * pos--;
		if (pos < 2)
			pos = 9;
	}
	resultado = soma % 11 < 2 ? 0 : 11 - soma % 11;
	if (resultado != digitos.charAt(0))
		return false;

	tamanho = tamanho + 1;
	numeros = cnpj.substring(0, tamanho);
	soma = 0;
	pos = tamanho - 7;
	for (i = tamanho; i >= 1; i--) {
		soma += numeros.charAt(tamanho - i) * pos--;
		if (pos < 2)
			pos = 9;
	}
	resultado = soma % 11 < 2 ? 0 : 11 - soma % 11;
	if (resultado != digitos.charAt(1))
		return false;

	return true;
}

function verificaCPFTodosSocios() {
	var correto = true;
	$('#divSocios .cpf-group').each(function() {
		if ($(this).hasClass('has-error')) {
			correto = false;
		}
	});

	if (correto) {
		return true;
	} else
		return false;

}