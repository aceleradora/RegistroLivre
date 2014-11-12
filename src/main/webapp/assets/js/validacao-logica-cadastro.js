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
		alert("Arquivo inexistente ou com a extensão inválida. (Somente PDF's são permitidos!)");
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

        CPF = cpfDigitado;
        if(!CPF){ return false;}
        erro  = new String;
        cpfv  = CPF;
        if(cpfv.length == 14 || cpfv.length == 11){
            cpfv = cpfv.replace('.', '');
            cpfv = cpfv.replace('.', '');
            cpfv = cpfv.replace('-', '');
  
            var nonNumbers = /\D/;
    
            if(nonNumbers.test(cpfv)){
                erro = "A verificacao de CPF suporta apenas números!";
            }else{
                if (cpfv == "00000000000" ||
                    cpfv == "11111111111" ||
                    cpfv == "22222222222" ||
                    cpfv == "33333333333" ||
                    cpfv == "44444444444" ||
                    cpfv == "55555555555" ||
                    cpfv == "66666666666" ||
                    cpfv == "77777777777" ||
                    cpfv == "88888888888" ||
                    cpfv == "99999999999") {
                            
                    erro = "Número de CPF inválido!"
                }
                var a = [];
                var b = new Number;
                var c = 11;
  
                for(i=0; i<11; i++){
                    a[i] = cpfv.charAt(i);
                    if (i < 9) b += (a[i] * --c);
                }
                if((x = b % 11) < 2){
                    a[9] = 0
                }else{
                    a[9] = 11-x
                }
                b = 0;
                c = 11;
                for (y=0; y<10; y++) b += (a[y] * c--);
    
                if((x = b % 11) < 2){
                    a[10] = 0;
                }else{
                    a[10] = 11-x;
                }
                if((cpfv.charAt(9) != a[9]) || (cpfv.charAt(10) != a[10])){
                    erro = "Número de CPF inválido.";
                }
            }
        }else{
            if(cpfv.length == 0){
                return false;
            }else{
                erro = "Número de CPF inválido.";
            }
        }
        if (erro.length > 0){
            $(this).val('');
            alert(erro);
            setTimeout(function(){$(this).focus();},100);
            return false;
        }
        return $(this);
	  
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