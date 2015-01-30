var RegistroLivre = RegistroLivre || {};

RegistroLivre.Mascaras = function mascaras(){
	var mascararCpf = function mascararCpf(cpf){		
		cpf.mask("999.999.999-99");
	}

	var mascararCnpj = function mascararCnpj(cnpj){		
		cnpj.mask("99.999.999/9999-99");
	}

	var mascararCep = function mascararCep(cep){		
		cep.mask("99999-999");
	}
	
	var mascararData = function mascararData(data){		
		data.mask("99/99/9999");
	}
	
	var mascararNumero = function mascararNumero(numero){		
		numero.mask("000000000000");
	}

	return{
		mascararCpf  : mascararCpf,
		mascararCnpj : mascararCnpj,
		mascararCep  : mascararCep, 
		mascararData : mascararData
	}	
}

var mascaras = new RegistroLivre.Mascaras();


