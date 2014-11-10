function validaCadastro() {

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
}

function validaCNPJTempoReal(cnpj){
	if(validarCNPJ(cnpj)){
		$("#cnpj-group").addClass("has-success has-feedback");		
		$("#cnpj-group").removeClass("has-error");
		
		$("#cnpj-group span").addClass("glyphicon-ok");
		$("#cnpj-group span").removeClass("glyphicon-remove");
		
	}else{		
		$("#cnpj-group").addClass("has-error");
		$("#cnpj-group").removeClass("has-success");
		
		$("#cnpj-group span").removeClass("glyphicon-ok");
		$("#cnpj-group span").addClass("glyphicon-remove");
	}
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

function IsNum(v) {

	var ValidChars = "0123456789";
	var IsNumber = true;
	var Char;

	for (i = 0; i < v.length; i++) {
		Char = v.charAt(i);
		if (ValidChars.indexOf(Char) == -1) {
			return false;
		}
	}
	return true;
}
