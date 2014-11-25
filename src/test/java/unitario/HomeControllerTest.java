package unitario;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
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
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import br.com.aceleradora.RegistroLivre.controller.HomeController;
import br.com.aceleradora.RegistroLivre.dao.EmpresaDAO;
import br.com.aceleradora.RegistroLivre.model.Empresa;
import br.com.caelum.vraptor.Result;

@RunWith(MockitoJUnitRunner.class)
public class HomeControllerTest {

	@Mock
	private EmpresaDAO empresaDAO;
	@Mock
	private Result result;
	HomeController homeController;
	Empresa empresa;
	List<Empresa> listaDeEmpresas;
	List<Empresa> listaDeEmpresasResultado;

	@Before
	public void setup() {
		homeController = new HomeController(empresaDAO, result);
		listaDeEmpresas = new ArrayList<Empresa>();
		//listaDeEmpresasResultado = new ArrayList<Empresa>();
		empresa = new Empresa();
		
		when(empresaDAO.getTodas()).thenReturn(listaDeEmpresas);
	}

	@Test
	public void quandoChamOMetodoBuscaPorCnpjOMetodoGetTodasDoDaoEmpresaEhChamado()
			throws Exception {
		String cnpj = "65.174.387/0001-48";

		homeController.buscaPorCnpj(cnpj);

		verify(empresaDAO).getTodas();
	}

	@Test
	public void quandoEncontraEmpresaComCnpjCorretoRetornaUmaLista() throws Exception {
		String cnpjRequerido = "73.144.566/0001-60";
		empresa.setCnpj("73.144.566/0001-60");
		listaDeEmpresas.add(empresa);

		listaDeEmpresasResultado = homeController.buscaPorCnpj(cnpjRequerido);

		assertEquals(cnpjRequerido, listaDeEmpresasResultado.get(0).getCnpj());
	}
	
	@Test
	public void quandoNaoEncontraNenhumaEmpresaRetornaUmaListaVazia() throws Exception {
		listaDeEmpresasResultado = homeController.buscaPorCnpj("73.144.566/0001-60");
		
		assertTrue(listaDeEmpresasResultado.isEmpty());
	}
	
	@Test
	public void quandoEncontraDuasOcorrenciasDaMesmaEmpresaRetornaUmaListaCom2Itens() throws Exception {
		String cnpj = "73.144.566/0001-60";
		empresa.setCnpj(cnpj);
		listaDeEmpresas.add(empresa);
		listaDeEmpresas.add(empresa);
		
		listaDeEmpresasResultado = homeController.buscaPorCnpj(cnpj);
		
		assertThat(listaDeEmpresasResultado.size(), is(2));
	}
	
	
}
