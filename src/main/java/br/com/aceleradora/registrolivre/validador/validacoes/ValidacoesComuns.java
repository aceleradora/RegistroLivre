package br.com.aceleradora.registrolivre.validador.validacoes;

public class ValidacoesComuns {	
	public static int verificaQuantidadeNumerosIguais(String texto) {
		int quantidadeDeNumerosIguais = 1;
		for (int i = 0; i < texto.length() - 1; i++) {
			if (texto.charAt(i) == texto.charAt(i + 1)) {
				quantidadeDeNumerosIguais++;
			}
		}
		return quantidadeDeNumerosIguais;
	}
}
