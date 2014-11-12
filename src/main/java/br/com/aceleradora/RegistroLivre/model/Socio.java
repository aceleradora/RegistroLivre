package br.com.aceleradora.RegistroLivre.model;

import java.util.InputMismatchException;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;

import br.com.aceleradora.RegistroLivre.dao.Entidade;

@Entity
@SequenceGenerator(initialValue=1, allocationSize=1, name="geradorId", sequenceName="socio_sequence")
public class Socio extends Entidade{
	
	private String nome;
	private String cpf;
	private boolean inativo;

	public Socio() { }
	
	public Socio(String nome, String cpf, boolean inativo){
		this.nome = nome;
		this.cpf = cpf;
		this.inativo = inativo;
	}
	
	public boolean isInativo() {
		return inativo;
	}

	public void setInativo(boolean inativo) {
		this.inativo = inativo;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getCpf(){
		return cpf;
	}
	
	public boolean getSituacaoDoSocio() {
		
		return inativo;
	}
	
	public void setSituacaoDoSocio(boolean situacaoDoSocio) {
		this.inativo = situacaoDoSocio;
	}
	
	public boolean inativaSocio() {
		
		inativo = false;
		
		return inativo;
	}

}