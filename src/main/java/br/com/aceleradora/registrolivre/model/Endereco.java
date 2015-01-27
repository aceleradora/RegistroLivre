package br.com.aceleradora.registrolivre.model;

import javax.persistence.Embeddable;
import javax.validation.constraints.Pattern;

import org.apache.commons.lang.StringEscapeUtils;

@Embeddable
public class Endereco {
	private String logradouro;
	@Pattern(regexp = "[0-9]+", message = "O número de endereço posto é inválido.")
	private String numero;
	private String complemento;
	private String cidade;
	private String uf;
	private String cep;
	
	public String getLogradouro() {
		return StringEscapeUtils.escapeHtml(logradouro);
	}
	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}
	public String getNumero() {
		return StringEscapeUtils.escapeHtml(numero);
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public String getComplemento() {
		return StringEscapeUtils.escapeHtml(complemento);
	}
	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}
	public String getCidade() {
		return StringEscapeUtils.escapeHtml(cidade);
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public String getUf() {
		return StringEscapeUtils.escapeHtml(uf);
	}
	public void setUf(String uf) {
		this.uf = uf;
	}
	public String getCep() {
		return StringEscapeUtils.escapeHtml(cep);
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	
	public boolean verificaCep(){
		
		String cepSemCaracteresEspeciais = cep.replace("-", "");
		
		if (cepSemCaracteresEspeciais.length() != 8)
			return false;			
		
		return true;
	}
}
