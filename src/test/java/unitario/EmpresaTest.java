package unitario;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;


import org.junit.Before;
import org.junit.Test;

import br.com.aceleradora.RegistroLivre.model.Empresa;

public class EmpresaTest {
	
	private Empresa empresa;

	@Before
	public void setup() {
		empresa = new Empresa();		
	}

	@Test
	public void retornaStringVaziaQuandoADataENula(){
		
		empresa.setDataCriacao(null);
						
		assertThat(empresa.getDataCriacao(), is(""));
	}

	@Test
	public void retornaStringNoFormatoDiaMesAnoQuandoDataEstaCorreta(){
		
		empresa.setDataCriacao("17/11/2004");
		
		assertThat(empresa.getDataCriacao(), is("17/11/2004"));
	}
	
}
