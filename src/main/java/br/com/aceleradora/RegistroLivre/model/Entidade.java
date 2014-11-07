package br.com.aceleradora.RegistroLivre.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class Entidade {
	
	@Id	
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator ="geradorId")	
	private long id;	
}
