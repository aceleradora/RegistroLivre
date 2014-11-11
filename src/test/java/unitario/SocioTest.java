package unitario;


import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import br.com.aceleradora.RegistroLivre.model.Socio;

public class SocioTest {

	Socio socio;
	
	@Before
	public void setUp(){
		socio = new Socio("Teste", "071.549.456-21", true);
	}
	
	
	@Test
	public void testaSeCpfEValido() throws Exception {
		
		boolean result = socio.verificarSeCpfEValido("071.549.456-21");
		
		assertTrue(result);
	}

}
