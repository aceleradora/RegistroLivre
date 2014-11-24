package unitario;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.when;
import br.com.aceleradora.RegistroLivre.controller.HomeController;
import br.com.aceleradora.RegistroLivre.dao.EmpresaDAO;
import br.com.aceleradora.RegistroLivre.model.Empresa;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.util.test.MockResult;

@RunWith(MockitoJUnitRunner.class)
public class HomeControllerTest {

	@Mock private EmpresaDAO empresaDAO;
	@Mock private Result result;
	HomeController homeController;
	Empresa empresa;
	List<Empresa> listaDeEmpresas;
	@Before
	public void setup(){
		 homeController = new HomeController(empresaDAO, result);
		 empresa = new Empresa();
		 empresa.setId(1);
		 empresa.setCnpj("65.174.387/0001-48");
		 listaDeEmpresas = new ArrayList<Empresa>();
		 listaDeEmpresas.add(empresa);
		 when(empresaDAO.getTodas()).thenReturn(listaDeEmpresas);
	}
	
	@Test
	public void quandoChamOMetodoBuscaPorCnpjOMetodoGetTodasDoDaoEmpresaEhChamado() throws Exception {
		String cnpj = "65.174.387/0001-48";
		
		homeController.buscaPorCnpj(cnpj);
		
		verify(empresaDAO).getTodas();
	}
	
	@Test
	public void quandoChamaOMetodoBuscaPorCnpjEEncontraAlgoRetornaUmaListaDeEmpresas() throws Exception {
		String cnpjRequerido = "65.174.387/0001-48";

		
		homeController.buscaPorCnpj(cnpjRequerido);
		
		verify(result).include(listaDeEmpresas);
		
	}
	
	@Test
	public void quandoNaoEncontraNada() throws Exception {
		Empresa novaEmpresa = new Empresa();
		novaEmpresa.setCnpj("29.882.673/0001-02");
		listaDeEmpresas.add(novaEmpresa);
		Long quantidadeDeRegistros = 0L;
		
		homeController.buscaPorCnpj("29.882.673/0001-02");
		
		verify(result).include("quantidadeDeRegistros", quantidadeDeRegistros);
		
		
	}
	
	
}
