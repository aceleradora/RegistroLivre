package br.com.aceleradora.RegistroLivre.model;

import java.util.ArrayList;
import java.util.List;

import br.com.caelum.vraptor.interceptor.multipart.UploadedFile;
import br.com.caelum.vraptor.ioc.Component;

@Component
public class Validador {

	public static List<Socio> retiraSociosNulos(List<Socio> socios) {
		List<Socio> sociosNaoNulos = new ArrayList<Socio>();
		
		for (int i = 0; i < socios.size(); i++) {
			if (!(socios.get(i).getNome() == null)) {
				sociosNaoNulos.add(socios.get(i));
			}
		}
		return sociosNaoNulos;
	}

	public static boolean verificaCpfListaSocio(List<Socio> socios) {
		for (Socio socio : socios) {
			if (socio.getCpf() != null) {
				if (!verificaCpf(socio.getCpf())) {
					return false;
				}
			}
		}
		return true;
	}

	public static boolean verificaCpf(String cpf) {
		char digito1, digito2;
		int soma, resto, numero, peso;

		String cpfSemCaracteresEspeciais = cpf.replaceAll("[.-]", "");

		if ((verificaNumerosIguaisCpf(cpfSemCaracteresEspeciais)) == 11){
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

	public static boolean verificaCnpj(String cnpj) {

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

		
		if ((verificaNumerosIguaisCnpj(cnpj))==14){
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

	public static boolean verificaNomeFantasia(String nomeFantasia) {
		if (nomeFantasia == null) {
			return false;
		}

		if (nomeFantasia.length() <= 1) {
			return false;
		}

		return true;
	}

	public static boolean verificaNumeroEndereco(Empresa empresa) {
		if(empresa.getEndereco() != null && empresa.getEndereco().getNumero() != null)
			return empresa.getEndereco().getNumero().matches("[0-9]+");
		return true;
	}

	public static boolean verificaExtensaoArquivo(UploadedFile arquivo) {
		if(arquivo == null){
			return false;
		}
		if(!arquivo.getFileName().contains(".pdf")){
			return false;
		}

		return true;
	}
	
	public static int verificaNumerosIguaisCnpj(String cnpj){
		int quantidadeDeNumerosIguaisCnpj = 1;
		for (int i=0; i < cnpj.length()-1; i++){
			if (cnpj.charAt(i) == cnpj.charAt(i+1)){
				quantidadeDeNumerosIguaisCnpj++;
			}
		}
		return quantidadeDeNumerosIguaisCnpj;
	}
	
	public static int verificaNumerosIguaisCpf(String cpf){
		int quantidadeDeNumerosIguaisCpf = 1;
		for (int i=0; i < cpf.length()-1; i++){
			if (cpf.charAt(i) == cpf.charAt(i+1)){
				quantidadeDeNumerosIguaisCpf++;
			}
		}
		return quantidadeDeNumerosIguaisCpf;
	}
}
