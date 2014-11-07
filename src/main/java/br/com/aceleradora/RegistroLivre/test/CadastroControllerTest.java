package br.com.aceleradora.RegistroLivre.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import br.com.aceleradora.RegistroLivre.controller.EmpresaController;

public class CadastroControllerTest {
	
	private EmpresaController cadastroController;
	
	@Before
	public void SetUp(){
		cadastroController = new EmpresaController();
	}

	@Test
	public void retornaTrueSeConsegueChamarATelaDeCadastro() {
		//boolean result = cadastroController.cadastro();
	}

}
