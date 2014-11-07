package br.com.aceleradora.RegistroLivre.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import br.com.aceleradora.RegistroLivre.model.Socio;

public class SocioTest {

	Socio socio;
	
	@Before
	public void setUp(){
		socio = new Socio();
	}
	
	@Test
	public void testaSeCpfEValido() throws Exception {
		
		boolean result = socio.verificarValidadeCpf("017.795.010-21");
		
		assertTrue(result);
	}

	@Test
	public void testaSeInativaSocio() throws Exception {
		
		boolean result = socio.inativaSocio();
		
		assertFalse(result);
	}
	
	@Test
	public void testaSeSocioInformadoEstaAtivoComoDefault() throws Exception {
		
		boolean result = socio.getSocio();
		
		assertTrue(result);
	}
}
