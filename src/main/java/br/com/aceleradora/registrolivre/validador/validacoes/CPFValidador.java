package br.com.aceleradora.registrolivre.validador.validacoes;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import br.com.aceleradora.registrolivre.validador.annotations.CPFValido;

public class CPFValidador implements ConstraintValidator<CPFValido, String> {

	@Override
	public void initialize(CPFValido arg0) {		

	}

	@Override
	public boolean isValid(String cpf, ConstraintValidatorContext contexto) {
		char digito1, digito2;
		int soma, resto, numero, peso;

		String cpfSemCaracteresEspeciais = cpf.replaceAll("[.-]", "");		

		if(cpfSemCaracteresEspeciais.length() != 11){
			return false;
		}

		if ((ValidacoesComuns.verificaQuantidadeNumerosIguais(cpfSemCaracteresEspeciais)) == 11) {
			return false;
		}
		
		soma = 0;
		peso = 10;

		for (int index = 0; index < 9; index++) {
			numero = (int) (cpfSemCaracteresEspeciais.charAt(index) - 48);
			soma = soma + (numero * peso);
			peso = peso - 1;
		}

		resto = 11 - (soma % 11);

		if ((resto == 10) || (resto == 11)) {
			digito1 = '0';
		} else {
			digito1 = (char) (resto + 48);
		}

		soma = 0;
		peso = 11;

		for (int index = 0; index < 10; index++) {
			numero = (int) (cpfSemCaracteresEspeciais.charAt(index) - 48);
			soma = soma + (numero * peso);
			peso = peso - 1;
		}

		resto = 11 - (soma % 11);

		if ((resto == 10) || (resto == 11)) {
			digito2 = '0';
		} else {
			digito2 = (char) (resto + 48);
		}

		if ((digito1 == cpfSemCaracteresEspeciais.charAt(9))
				&& (digito2 == cpfSemCaracteresEspeciais.charAt(10))) {
			return true;
		} else {
			return false;
		}
	}	
}