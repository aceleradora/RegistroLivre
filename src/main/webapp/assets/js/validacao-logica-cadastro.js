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

	if (formulario.cpf.value != "") {
		alert("Preencha o CPF do sócio " + formulario.nome - socio.value
				+ " corretamente.");
		formulario.nomeFantasia.focus();
		return false;
	}

	if (!validarPdf(formulario.file)) {
		alert("Arquivo inexistente ou com a extensão inválida. (Somente PDF's são permitidos!)");
		return false;
	}	
}

function validarPdf(objFileControl) {

	var file = objFileControl.value;
	var len = file.length;
	var ext = file.slice(len - 4, len);

	return ext.toUpperCase() == ".PDF";
}

function validarCpf(cpf) {

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
