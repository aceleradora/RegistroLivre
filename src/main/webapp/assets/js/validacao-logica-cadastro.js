function validarCadastro(editar) {

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

	if (!editar) {
		if (!validarExistePdf(formulario.file)) {
			alert("Arquivo PDF inexistente.");
			return false;
		}
	}

	if (validarExistePdf(formulario.file) && !validarExtensaoPdf(formulario.file)) {
		alert("Arquivo com a extensão inválida. (Somente PDF's são permitidos.)");
		return false;
	}

	if (formulario.cpf != null) {
		if (!validarCpf(formulario.cpf.value)) {
			alert("Preencha o CPF do socio corretamente!");

			formulario.cpf.focus();
			return false;
		}
	}

}

function validarExistePdf(objFileControl) {

	var file = objFileControl.value;

	return file != "";
}

function validarExtensaoPdf(objFileControl) {

	var file = objFileControl.value;
	var len = file.length;
	var ext = file.slice(len - 4, len);

	return ext.toUpperCase() == ".PDF";
}

function todosCaracteresSaoIguais(palavra){
	for (i = 0; i < palavra.length -1; i++){
		if (palavra.charAt(i) != palavra.charAt(i+1)){
			return false;
		}
	}	
	
	return true;
}

function validarCpf(cpfDigitado) {

	var cpf = cpfDigitado.replace(/[.\-]/g, '');

	erro = new String;
	
	if (todosCaracteresSaoIguais(cpf))
		return false;
	
	var digitosCpf = cpf.split('');
	var acumuladorDigitos = 0;
	var peso = 11;

	var primeiroDigitoVerificador = digitosCpf[9];
	var segundoDigitoVerificador = digitosCpf[10];

	var primeiroDigitoVerificadorCalculado = 0;
	var segundoDigitoVerificadorCalculado = 0;

	for (var i = 0; i < 9; i++) {
		acumuladorDigitos += (digitosCpf[i] * --peso);
	}

	var resto = acumuladorDigitos % 11;

	primeiroDigitoVerificadorCalculado = (resto < 2) ? 0 : 11 - resto;

	acumuladorDigitos = 0;
	peso = 11;

	for (var i = 0; i < 10; i++) {
		acumuladorDigitos += (digitosCpf[i] * peso--);
	}

	resto = acumuladorDigitos % 11;

	segundoDigitoVerificadorCalculado = (resto < 2) ? 0 : 11 - resto;

	return !(primeiroDigitoVerificador != primeiroDigitoVerificadorCalculado || segundoDigitoVerificador != segundoDigitoVerificadorCalculado);
}

function validarCNPJ(cnpjDigitado) {

	cnpj = cnpjDigitado.replace(/[^\d]+/g, '');

	if (cnpj == '')
		return false;

	if (cnpj.length != 14)
		return false;

	if (todosCaracteresSaoIguais(cnpj))
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
	} else {
		return false;
	}
}