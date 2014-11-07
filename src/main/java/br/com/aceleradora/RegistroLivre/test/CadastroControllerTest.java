package br.com.aceleradora.RegistroLivre.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import br.com.aceleradora.RegistroLivre.controller.CadastroController;

public class CadastroControllerTest {
	
	private CadastroController cadastroController;
	
	@Before
	public void SetUp(){
		cadastroController = new CadastroController();
	}

	@Test
	public void retornaTrueSeConsegueChamarATelaDeCadastro() {
		//boolean result = cadastroController.cadastro();
	}

}
