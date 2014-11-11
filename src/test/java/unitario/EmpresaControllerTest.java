package unitario;


import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.hibernate.Session;
import org.junit.Ignore;
import org.junit.Test;

import br.com.aceleradora.RegistroLivre.controller.EmpresaController;
import br.com.aceleradora.RegistroLivre.dao.EmpresaDAO;
import br.com.aceleradora.RegistroLivre.model.Empresa;
import br.com.caelum.vraptor.util.test.MockResult;
public class EmpresaControllerTest {

	Session session;
	EmpresaDAO empresaDAO;
	MockResult result; 
	
	private void setup() {
		result = new MockResult();
	}
	
	@Ignore
	@Test
	public void quandoChamaOMetodoCadastrarRedirecionaParaVisualizacao() throws Exception {
		Empresa empresa = new Empresa();
		empresa.setRazaoSocial("blablabla");
		empresa.setCnpj("212321");
		EmpresaController empresaController = new EmpresaController(empresaDAO, result);
		
		empresaController.cadastrar(empresa);
		
		assertTrue(result.included().containsKey("Visualização de Empresa"));
	}

	
	@Ignore
	@Test
	public void quandoooChamaOMetodoListagemRetornaTodasEmpresas() throws Exception {
		
		EmpresaDAO empresaDAO = new EmpresaDAO(session);
		EmpresaController empresaController = new EmpresaController(empresaDAO, result);
		
		List<Empresa> listagem = empresaController.listagem();
		
		assertNotNull(listagem);
		
	}
}
