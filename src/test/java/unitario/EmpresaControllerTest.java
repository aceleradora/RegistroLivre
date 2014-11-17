package unitario;


import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import br.com.aceleradora.RegistroLivre.controller.EmpresaController;
import br.com.aceleradora.RegistroLivre.dao.EmpresaDAO;
import br.com.aceleradora.RegistroLivre.model.Empresa;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.util.test.MockValidator;
import br.com.caelum.vraptor.validator.ValidationException;

@RunWith(MockitoJUnitRunner.class)
public class EmpresaControllerTest {

	@Mock
	Result result;	
	@Mock
	EmpresaDAO empresaDAO;
	EmpresaController empresaController;	
	List<Empresa> listaDeEmpresas;
	@Mock
	Empresa empresa;
	@Mock
	Validator validator;
	
	@Mock
	EmpresaController empresaControllerMock;

	@Before
	public void setup() {
		result = mock(Result.class);
		validator = mock(Validator.class);
		empresa = mock(Empresa.class);
		empresaController = new EmpresaController(empresaDAO, result, validator);			
	}
		
	@Test
	public void quandoChamaOMetodoListagemRetornaOMetodoGetTodasDoDAO() throws Exception {
		listaDeEmpresas = new ArrayList<Empresa>();
		listaDeEmpresas.add(empresa);
		
		when(empresaDAO.getTodas()).thenReturn(listaDeEmpresas);	
		
		empresaController.listagem();
		
		verify(empresaDAO).getTodas();
	}
	
	@Test
	public void quandoChamaOMetodoVisualizacaoRetornaOMetodoGetByIdDoDAO() throws Exception {
		when(empresa.getId()).thenReturn(1L);
		empresa.setId(1);
		empresaController.visualizacao(empresa);	
		
		verify(empresaDAO).getById(empresa.getId());		
	}
	
}
