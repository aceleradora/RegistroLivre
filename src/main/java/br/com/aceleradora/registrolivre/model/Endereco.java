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
		return logradouro;
	}
	public void setLogradouro(String logradouro) {
		this.logradouro = StringEscapeUtils.escapeHtml(logradouro);
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = StringEscapeUtils.escapeHtml(numero);
	}
	public String getComplemento() {
		return complemento;
	}
	public void setComplemento(String complemento) {
		this.complemento = StringEscapeUtils.escapeHtml(complemento);
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = StringEscapeUtils.escapeHtml(cidade);
	}
	public String getUf() {
		return uf;
	}
	public void setUf(String uf) {
		this.uf = StringEscapeUtils.escapeHtml(uf);
	}
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = StringEscapeUtils.escapeHtml(cep);
	}
	
	public boolean verificaCep(){
		
		String cepSemCaracteresEspeciais = cep.replace("-", "");
		
		if (cepSemCaracteresEspeciais.length() != 8)
			return false;			
		
		return true;
	}
}
