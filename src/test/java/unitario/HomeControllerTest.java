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
		empresa = new Empresa();
	}

	@Test
	public void quandoChamaOMetodoBuscaPorCnpjOMetodoPesquisaPorCnpjDoDaoEmpresaEhChamado()
			throws Exception {
		String cnpj = "65.174.387/0001-48";
		when(empresaDAO.pesquisaPorCnpj(cnpj)).thenReturn(listaDeEmpresas);

		homeController.buscaPorCnpj(cnpj);

		verify(empresaDAO).pesquisaPorCnpj(cnpj);
	}

	@Test
	public void quandoEncontraEmpresaComCnpjCorretoRetornaUmaLista()
			throws Exception {
		String cnpjRequerido = "73.144.566/0001-60";
		when(empresaDAO.pesquisaPorCnpj(cnpjRequerido)).thenReturn(
				listaDeEmpresas);

		empresa.setCnpj("73.144.566/0001-60");
		listaDeEmpresas.add(empresa);

		listaDeEmpresasResultado = homeController.buscaPorCnpj(cnpjRequerido);

		assertEquals(cnpjRequerido, listaDeEmpresasResultado.get(0).getCnpj());
	}

	@Test
	public void quandoNaoEncontraNenhumaEmpresaRetornaUmaListaVazia()
			throws Exception {
		String cnpjRequerido = "73.144.566/0001-60";
		String cnpjBuscado = "65.174.387/0001-48";
		empresa.setCnpj(cnpjRequerido);
		when(empresaDAO.pesquisaPorCnpj(cnpjRequerido)).thenReturn(listaDeEmpresas);
		listaDeEmpresas.add(empresa);

		listaDeEmpresasResultado = homeController.buscaPorCnpj(cnpjBuscado);

		assertFalse(listaDeEmpresasResultado.contains(empresa));
	}

	@Test
	public void quandoEncontraDuasOcorrenciasDaMesmaEmpresaRetornaUmaListaCom2Itens()
			throws Exception {
		String cnpjRequerido = "73.144.566/0001-60";
		empresa.setCnpj(cnpjRequerido);
		when(empresaDAO.pesquisaPorCnpj(cnpjRequerido)).thenReturn(listaDeEmpresas);
		listaDeEmpresas.add(empresa);
		listaDeEmpresas.add(empresa);

		listaDeEmpresasResultado = homeController.buscaPorCnpj(cnpjRequerido);

		assertThat(listaDeEmpresasResultado.size(), is(2));
	}

}