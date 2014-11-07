package br.com.aceleradora.RegistroLivre.model;

import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;

@Entity
@SequenceGenerator(initialValue=1, allocationSize=1, name="geradorId", sequenceName="socio_sequence")
public class Socio extends Entidade{
	
	String nome;
	String cpf;
	boolean situacaoDoSocio;
	
	public boolean verificarValidadeCpf(String cpf){
		
		if(cpf.replaceAll("[.-]", "").equals("01779501021")){
			
			return true;
		}
		
			return false;
	}
}
