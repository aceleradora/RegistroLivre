package unitario;


import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.hibernate.Session;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mock;

import java.util.List;

import br.com.aceleradora.RegistroLivre.controller.EmpresaController;
import br.com.aceleradora.RegistroLivre.dao.EmpresaDAO;
import br.com.aceleradora.RegistroLivre.model.Empresa;
import br.com.caelum.vraptor.util.test.MockResult;

public class EmpresaControllerTest {

	@Mock
	Session session;
	
	private void setup() {
		
	}
	
	@Test
	public void testeTeste() throws Exception {
		assertTrue(true);
	}
	
	@Test
	public void testeTeste2() throws Exception {
		assertThat(1+1, is(2));
	}
	
	@Ignore
	@Test
	public void quandoChamaOMetodoListagemRetornaTodasEmpresas() throws Exception {
		MockResult result = new MockResult();
		
		EmpresaDAO empresaDAO = new EmpresaDAO(session);
		EmpresaController empresaController = new EmpresaController(empresaDAO);
		
		List<Empresa> listagem = empresaController.listagem(result);
		
		assertNotNull(listagem);
		
	}
}
