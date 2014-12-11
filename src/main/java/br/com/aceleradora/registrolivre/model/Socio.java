package br.com.aceleradora.registrolivre.model;

import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;

import br.com.aceleradora.registrolivre.dao.Entidade;

@Entity
@SequenceGenerator(initialValue = 1, allocationSize = 1, name = "geradorId", sequenceName = "socio_sequence")
public class Socio extends Entidade {
	private String nome;
	private String cpf;
	private boolean ativo;

	public Socio() {
	}

	public Socio(String nome, String cpf) {
		this.nome = nome;
		this.cpf = cpf;
	}

	public Socio(String nome, String cpf, boolean ativo) {
		this.nome = nome;
		this.cpf = cpf;
		this.ativo = ativo;
	}

	public String getNome() {
		return nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public boolean getAtivo() {
		return ativo;
	}
}