package br.com.aceleradora.registrolivre.dao;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class Entidade {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "geradorId")
	private long id;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

}
