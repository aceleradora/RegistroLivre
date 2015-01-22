package br.com.aceleradora.registrolivre.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import org.apache.commons.lang.StringEscapeUtils;

import br.com.aceleradora.registrolivre.validador.annotations.CPFValido;

@Entity
public class Socio{
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "geradorId")
	@SequenceGenerator(initialValue = 1, allocationSize = 1, name = "geradorId", sequenceName = "socio_sequence")
	private long id;
	
	private String nome;
	@CPFValido
	private String cpf;
	private boolean ativo;

	public Socio() {
	}

	public Socio(String nome, String cpf) {
		this.nome = StringEscapeUtils.escapeHtml(nome);
		this.cpf = StringEscapeUtils.escapeHtml(cpf);
	}

	public Socio(String nome, String cpf, boolean ativo) {
		this.nome = StringEscapeUtils.escapeHtml(nome);
		this.cpf = StringEscapeUtils.escapeHtml(cpf);
		this.ativo = ativo;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = StringEscapeUtils.escapeHtml(cpf);
	}

	public boolean getAtivo() {
		return ativo;
	}
}