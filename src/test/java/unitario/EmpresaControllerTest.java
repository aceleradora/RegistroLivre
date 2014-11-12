package unitario;


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
import br.com.caelum.vraptor.util.test.MockResult;

@RunWith(MockitoJUnitRunner.class)
public class EmpresaControllerTest {

	MockResult result;
		
	@Mock
	EmpresaDAO empresaDAO; 
	EmpresaController empresaController;
	Empresa empresa;
	List<Empresa> listaDeEmpresas;
	Result resultRedirect;
	
	@Before
	public void setup() {
		result = new MockResult();
		empresaController = new EmpresaController(empresaDAO, result);
		empresa = new Empresa();
		listaDeEmpresas = new ArrayList<Empresa>();
		listaDeEmpresas.add(empresa);
		when(empresaDAO.getTodas()).thenReturn(listaDeEmpresas);		

	}
		
	@Test
	public void quandoChamaOMetodoListagemRetornaOMetodoGetTodasDoDAO() throws Exception {
		
		empresaController.listagem();
		
		verify(empresaDAO).getTodas();
	}
	
	@Test
	public void quandoChamaOMetodoVisualizacaoRetornaOMetodoGetByIdDoDAO() throws Exception {
		empresa.setId(1);
		empresaController.visualizacao(empresa);	
		
		verify(empresaDAO).getById(empresa.getId());		
	}
	
	@Ignore
	@Test	
	public void quandoChamaOMetodoCadastrarDeveRetornarParaPaginaDeVisualizacaoDeEmpresa() throws Exception {
		empresaDAO.adiciona(empresa);

		when(resultRedirect.redirectTo(EmpresaController.class)).thenReturn(empresaController);		
		verify(resultRedirect).redirectTo(EmpresaController.class);
	}
	
}
