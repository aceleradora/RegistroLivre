package unitario;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import br.com.aceleradora.RegistroLivre.controller.EmpresaController;
import br.com.aceleradora.RegistroLivre.dao.EmpresaDAO;
import br.com.aceleradora.RegistroLivre.model.Empresa;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;

@RunWith(MockitoJUnitRunner.class)
public class EmpresaControllerTest {
	@Mock private Result result;	
	@Mock private EmpresaDAO empresaDAO;
	private Empresa empresa;
	@Mock private Validator validator;
	private EmpresaController empresaController;	
	private List<Empresa> listaDeEmpresas;

	@Before
	public void setup() {
		empresa = new Empresa();
		empresaController = new EmpresaController(empresaDAO, result, validator);			
	}
		
	@Test
	public void quandoChamaOMetodoListagemRetornaOMetodoGetTodasDoDAO() throws Exception {
		listaDeEmpresas = new ArrayList<Empresa>();
		listaDeEmpresas.add(empresa);
		
		when(empresaDAO.getTodas()).thenReturn(listaDeEmpresas);	
		
		List<Empresa> listagem = empresaController.listagem();
		
		verify(empresaDAO).getTodas();
		assertThat(listagem, is(listaDeEmpresas));
	}
	
	@Test
	public void quandoChamaOMetodoListagemChamaOIncludeDoResult() throws Exception {
		Long quantidadeRegistros = 5L;
		when(empresaDAO.contaQuantidadeDeRegistros()).thenReturn(quantidadeRegistros);
		
		empresaController.listagem();
		
		verify(result).include("totalDeRegistros", quantidadeRegistros);
	}
	
	@Test
	public void quandoChamaOMetodoVisualizacaoRetornaOMetodoGetByIdDoDAO() throws Exception {
		empresa.setId(1);
		empresaController.visualizacao(empresa);	
		
		verify(empresaDAO).getById(empresa.getId());		
	}
	
	@Test
	public void quandoChamaOMetodoCadastroRetornaOMetodoGetByIdDoDAO() throws Exception {
		empresa.setId(1);
		empresaController.cadastro(empresa);
		
		verify(empresaDAO).getById(empresa.getId());	
	}
	
	@Test
	public void quandoChamaOMetodoCadastroIncluiEditarNoResult() throws Exception {
		empresa.setId(1);
		empresaController.cadastro(empresa);
		
		verify(result).include("editar", true);
	}
}