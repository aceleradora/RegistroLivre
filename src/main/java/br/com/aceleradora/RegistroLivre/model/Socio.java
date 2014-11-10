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

	public Socio(String nome, String cpf){
		this.setNome(nome);
		this.cpf = cpf;
		this.inativo = false;
	}
	
	public Socio() {	}

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
	
	public boolean verificarSeCpfEValido(String cpf){
		
		char Digito1, Digito2;
        int Soma, i, r, Numero, Peso; 
        
		String cpfSemCaracteresEspeciais = cpf.replaceAll("[.-]", "");
		
		if (cpfSemCaracteresEspeciais.equals("00000000000") || cpfSemCaracteresEspeciais.equals("11111111111") 
				|| cpfSemCaracteresEspeciais.equals("22222222222") || cpfSemCaracteresEspeciais.equals("33333333333")
                || cpfSemCaracteresEspeciais.equals("44444444444") || cpfSemCaracteresEspeciais.equals("55555555555")
                || cpfSemCaracteresEspeciais.equals("66666666666") || cpfSemCaracteresEspeciais.equals("77777777777")
                || cpfSemCaracteresEspeciais.equals("88888888888") || cpfSemCaracteresEspeciais.equals("99999999999")
                || (cpfSemCaracteresEspeciais.length() != 11)){
			
            return false;}
		
        try {
        	Soma = 0;
            Peso = 10;
            
            for (i = 0; i < 9; i++) {
               
                Numero = (int) (cpfSemCaracteresEspeciais.charAt(i) - 48);
                Soma = Soma + (Numero * Peso);
                Peso = Peso - 1;
            }
       
            r = 11 - (Soma % 11);
            
            if ((r == 10) || (r == 11)) {  
                Digito1 = '0';              
            } else {
                Digito1 = (char) (r + 48);  
            }                               

            Soma = 0;
            Peso = 11;
            
            for (i = 0; i < 10; i++) {
          
                Numero = (int) (cpfSemCaracteresEspeciais.charAt(i) - 48);
                Soma = Soma + (Numero * Peso);
                Peso = Peso - 1;
            }
      
            r = 11 - (Soma % 11);
            
            if ((r == 10) || (r == 11)) {   
                Digito2 = '0';              
            } else {
                Digito2 = (char) (r + 48);  
            }                              

            if ((Digito1 == cpfSemCaracteresEspeciais.charAt(9)) && (Digito2 == cpfSemCaracteresEspeciais.charAt(10))) {
                return true;  
            } else {            
                return false; 
            }
        } catch (InputMismatchException erro) { 
            
        	return false;                     
        }
        
	}


	

}