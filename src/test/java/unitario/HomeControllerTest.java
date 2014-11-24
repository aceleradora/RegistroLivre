package unitario;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import br.com.aceleradora.RegistroLivre.controller.HomeController;
import br.com.aceleradora.RegistroLivre.dao.EmpresaDAO;
import br.com.caelum.vraptor.Result;

@RunWith(MockitoJUnitRunner.class)
public class HomeControllerTest {

	@Mock private EmpresaDAO empresaDAO;
	@Mock private Result result;
	
	@Test
	public void quandoChamOMetodoBuscaPorCnpjENaoEncontraEmpresaRetornaParaHome() throws Exception {
		HomeController homeController = new HomeController(empresaDAO, result);
		String cnpj = "65.174.387/0001-48";
		
		homeController.buscaPorCnpj(cnpj);
		
		verify(empresaDAO).getTodas();
	}
}
