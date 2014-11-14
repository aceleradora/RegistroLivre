package unitario;


import static org.mockito.Mockito.spy;
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
import br.com.caelum.vraptor.util.test.MockResult;
import br.com.caelum.vraptor.util.test.MockValidator;

@RunWith(MockitoJUnitRunner.class)
public class EmpresaControllerTest {

	MockResult result;	
	@Mock
	EmpresaDAO empresaDAO;
	EmpresaController empresaController;
	Empresa empresa;
	List<Empresa> listaDeEmpresas;
	
	MockValidator validator;
	
	
//	@Mock
//	Result resultRedirect;
//	
	@Mock
	EmpresaController empresaControllerMock;

	@Before
	public void setup() {
		result = spy(new MockResult());
		validator = spy(new MockValidator());
		
		empresaController = new EmpresaController(empresaDAO, result, validator);
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
	public void quandoChamaOMetodoCadastrarEPassaUmCnpjInvalidoDeveRetornarParaPaginaDeVisualizacaoDeEmpresa() throws Exception {
		empresa.setCnpj("47960950/0449-27");
	
		empresaController.cadastrar(empresa);
		
		
		//when(result.redirectTo(empresaController)).thenReturn(empresaController);
		String teste = "/teste";
		verify(result).redirectTo(teste);

	}
	
}
