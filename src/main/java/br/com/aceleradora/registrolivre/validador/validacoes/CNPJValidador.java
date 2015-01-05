package br.com.aceleradora.registrolivre.validador.validacoes;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import br.com.aceleradora.registrolivre.validador.annotations.CNPJValido;

public class CNPJValidador implements ConstraintValidator<CNPJValido, String> {

	@Override
	public void initialize(CNPJValido arg0) {
	}

	@Override
	public boolean isValid(String cnpj, ConstraintValidatorContext constraintContext) {
		if (cnpj == null) {
			return false;
		}

		cnpj = cnpj.replaceAll("[./-]", "");

		if (cnpj == "") {
			return false;
		}

		if (cnpj.length() != 14) {
			return false;
		}

		if ((ValidacoesComuns.verificaQuantidadeNumerosIguais(cnpj)) == 14) {
			return false;
		}

		int tamanho = cnpj.length() - 2;
		String numeros = cnpj.substring(0, tamanho);
		String digitos = cnpj.substring(tamanho);

		int soma = 0;
		int pos = tamanho - 7;
		for (int i = tamanho; i >= 1; i--) {
			soma += Integer.parseInt(Character.toString(numeros.charAt(tamanho
					- i)))
					* pos--;

			if (pos < 2) {
				pos = 9;
			}
		}

		int resultado = soma % 11 < 2 ? 0 : 11 - soma % 11;

		if (resultado != Integer
				.parseInt(Character.toString(digitos.charAt(0)))) {
			return false;
		}

		tamanho = tamanho + 1;
		numeros = cnpj.substring(0, tamanho);
		soma = 0;
		pos = tamanho - 7;

		for (int i = tamanho; i >= 1; i--) {
			soma += Integer.parseInt(Character.toString(numeros.charAt(tamanho
					- i)))
					* pos--;

			if (pos < 2) {
				pos = 9;
			}
		}

		resultado = soma % 11 < 2 ? 0 : 11 - soma % 11;
		if (resultado != Integer
				.parseInt(Character.toString(digitos.charAt(1)))) {
			return false;
		}

		return true;
	}
}
