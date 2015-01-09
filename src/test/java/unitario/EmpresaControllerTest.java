package unitario;

import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import static org.mockito.Mockito.*;

import br.com.aceleradora.registrolivre.controller.EmpresaController;
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
	public void quandoChamaOMetodoCadastroIncluiEditarNoResult()
			throws Exception {
		empresa.setId(1);
		empresa.setUrl("http://res.cloudinary.com/dhqchbqit/image/upload/v1419266215/");
		when(empresaDAO.getById(1)).thenReturn(empresa);
		empresaController.cadastro(empresa);

		verify(result).include("editar", true);
		verify(result).include("nomeArquivoAntigo", empresa.getUrl().substring(61));

	}


}