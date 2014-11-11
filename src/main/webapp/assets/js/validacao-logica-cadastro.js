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
	
	console.log("Aqui é o CPF: " + validarCpf(formulario.cpf.value));

	if (!validarCpf(formulario.cpf.value)) {
		alert("Preencha o CPF do socio corretamente!");

		formulario.cpf.focus();
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

function validarCpf(cpfDigitado) {
	
	if(cpfDigitado == '')
		return true;

	exp = /\.|\-/g;
	var cpf = cpfDigitado.toString().replace(exp, "");
	
	if (cpf == "00000000000" || cpf.length != 11){
		return false;}
	
	var digitoDigitado = eval(cpf.charAt(9) + cpf.charAt(10));
	var soma1 = 0, soma2 = 0;
	var vlr = 11;

	for (i = 0; i < 9; i++) {
		soma1 += eval(cpf.charAt(i) * (vlr - 1));
		soma2 += eval(cpf.charAt(i) * vlr);
		vlr--;
	}
	soma1 = (((soma1 * 10) % 11) == 10 ? 0 : ((soma1 * 10) % 11));
	soma2 = (((soma2 + (2 * soma1)) * 10) % 11);

	var digitoGerado = (soma1 * 10) + soma2;
	
	return !(digitoGerado != digitoDigitado);

//	if (digitoGerado != digitoDigitado) {
//		alert('CPF Inválido!!');
//		return false;
//	} else {
//		alert('CPF Válido!');
//		return true;
//	}

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
