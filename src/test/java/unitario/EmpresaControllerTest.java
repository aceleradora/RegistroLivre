package unitario;


import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import br.com.aceleradora.RegistroLivre.controller.EmpresaController;
import br.com.aceleradora.RegistroLivre.dao.EmpresaDAO;
import br.com.aceleradora.RegistroLivre.model.Empresa;
import br.com.caelum.vraptor.util.test.MockResult;

@RunWith(MockitoJUnitRunner.class)
public class EmpresaControllerTest {

	MockResult result;
	
	@Mock
	EmpresaDAO empresaDAO; 

	
	@Before
	public void setup() {
		result = new MockResult();

	}
		
	@Test
	public void quandoChamaOMetodoListagemRetornaOMetodoGetTodasDoDAO() throws Exception {
		EmpresaController empresaController = new EmpresaController(empresaDAO, result);
		Empresa empresa = new Empresa();
		List<Empresa> listaDeEmpresas = new ArrayList<Empresa>();
		listaDeEmpresas.add(empresa);
		when(empresaDAO.getTodas()).thenReturn(listaDeEmpresas);		
		
		empresaController.listagem();
		
		verify(empresaDAO).getTodas();

	}	
	
}
