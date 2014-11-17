package unitario;


import static org.mockito.Mockito.spy;
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
import br.com.caelum.vraptor.util.test.MockResult;
import br.com.caelum.vraptor.util.test.MockValidator;

@RunWith(MockitoJUnitRunner.class)
public class EmpresaControllerTest {

	MockResult result;	
	@Mock
	EmpresaDAO empresaDAO;
	EmpresaController empresaController;	
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
	}
		
	@Test
	public void quandoChamaOMetodoListagemRetornaOMetodoGetTodasDoDAO() throws Exception {
		Empresa empresa = new Empresa();
		listaDeEmpresas = new ArrayList<Empresa>();
		listaDeEmpresas.add(empresa);
		
		when(empresaDAO.getTodas()).thenReturn(listaDeEmpresas);	
		
		empresaController.listagem();
		
		verify(empresaDAO).getTodas();
	}
	
	@Test
	public void quandoChamaOMetodoVisualizacaoRetornaOMetodoGetByIdDoDAO() throws Exception {
		Empresa empresa = new Empresa();
		
		empresa.setId(1);
		empresaController.visualizacao(empresa);	
		
		verify(empresaDAO).getById(empresa.getId());		
	}
	
	
	@Test	
	public void quandoChamaOMetodoCadastrarEPassaUmCnpjENomeFantasiaValidosDeveRetornarParaPaginaDeVisualizacaoDeEmpresa() throws Exception {
		Empresa empresa = new Empresa();
		
		empresa.setCnpj("47960950/0449-27");
		empresa.setNomeFantasia("Teste");
			
		EmpresaController empresaControllerMock = mock(EmpresaController.class);
		when(result.redirectTo(empresaController)).thenReturn(empresaControllerMock);
		
		empresaController.cadastrar(empresa);
		
		verify(empresaControllerMock).visualizacao(empresa);
	}
	
	@Ignore
	@Test
	public void quandoChamaOMetodoCadastrarEPassaUmaEmpresaInvalidaDeveRetornarAPaginaCadastro(){
		Empresa empresa = new Empresa();
		empresa.setCnpj("47960950/0449-27");
		empresa.setNomeFantasia("Teste");
			
		EmpresaController empresaControllerMock = mock(EmpresaController.class);
		when(validator.onErrorUsePageOf(empresaController)).thenReturn(empresaControllerMock);
		
		empresaController.cadastrar(empresa);
	
		verify(empresaControllerMock).cadastro();
	}
	
	
}
