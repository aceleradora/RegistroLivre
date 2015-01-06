package br.com.aceleradora.registrolivre.validador.validacoes;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import br.com.aceleradora.registrolivre.validador.annotations.CPFValido;
import br.com.aceleradora.registrolivre.validador.annotations.NomeFantasiaValido;

public class NomeFantasiaValidador implements ConstraintValidator<NomeFantasiaValido, String> {

	@Override
	public void initialize(NomeFantasiaValido cpf) {
	}

	@Override
	public boolean isValid(String nomeFantasia, ConstraintValidatorContext arg1) {		
		if (nomeFantasia == null) {
			return false;
		}

		if (nomeFantasia.length() <= 1) {
			return false;
		}

		return true;
	}

}
