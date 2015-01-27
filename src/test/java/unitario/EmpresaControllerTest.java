package unitario;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import br.com.aceleradora.registrolivre.controller.EmpresaController;
import br.com.aceleradora.registrolivre.controller.HomeController;
import br.com.aceleradora.registrolivre.dao.EmpresaDAO;
import br.com.aceleradora.registrolivre.model.Empresa;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;

@RunWith(MockitoJUnitRunner.class)
public class EmpresaControllerTest {
	@Mock private Result result;
	@Mock private EmpresaDAO empresaDAO;
	@Mock private Validator validator;
	private Empresa empresa;
	private EmpresaController empresaController;

	@Before
	public void setup() {
		empresa = new Empresa();
		empresaController = new EmpresaController(empresaDAO, result, validator);
	}

	@Test
	public void quandoChamaOMetodoVisualizacaoRetornaOMetodoGetByIdDoDAO()
			throws Exception {
		empresa.setId(1);

		empresaController.visualizacao(empresa);

		verify(empresaDAO).getById(empresa.getId());
	}

	@Test
	public void quandoChamaOMetodoCadastroRetornaOMetodoGetByIdDoDAO()
			throws Exception {
		empresa.setId(1);
		empresa.setUrl("http://res.cloudinary.com/dhqchbqit/image/upload/v1419266215/");
		when(empresaDAO.getById(empresa.getId())).thenReturn(empresa);
		empresaController.cadastro(empresa);

		verify(empresaDAO).getById(empresa.getId());
	}

	@Test
	public void quandoChamaOMetodtoCadastroIncluiEditarNoResult()
			throws Exception {
		empresa.setId(1);
		empresa.setUrl("http://res.cloudinary.com/dhqchbqit/image/upload/v1419266215/");
		when(empresaDAO.getById(1)).thenReturn(empresa);
		empresaController.cadastro(empresa);

		verify(result).include("editar", true);
		verify(result).include("nomeArquivoAntigo", empresa.getUrl().substring(61));
	}
	
	@Test
	public void incluiVariavelNoReultadoSeABuscaForVazia() throws Exception {
		HomeController homeControllerMock = Mockito.mock(HomeController.class);
		when(result.redirectTo(HomeController.class)).thenReturn(homeControllerMock);
		when(result.include("buscaVazia", true)).thenReturn(result);
		
		empresaController.busca(null);

		verify(result).include("buscaVazia", true);
	}

	@Test
	public void buscaListaDeEmpresasNoBancoSeABuscaNaoForNula() throws Exception {
		EmpresaController empresaControllerMock = Mockito.mock(EmpresaController.class);
		String parametro = "abc";
		when(result.forwardTo(empresaController)).thenReturn(empresaControllerMock);
		when(empresaDAO.pesquisa(parametro)).thenReturn(new ArrayList<Empresa>());
		
		empresaController.busca(parametro);
		
		verify(empresaDAO).pesquisa(parametro);
	}
}