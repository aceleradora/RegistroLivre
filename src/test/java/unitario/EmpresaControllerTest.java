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
	@Mock private Validator validator;
	private Empresa empresa;
	private EmpresaController empresaController;
	private List<Empresa> listaDeEmpresas;
	private List<Empresa> listaDeEmpresasPaginacao;

	@Before
	public void setup() {
		empresa = new Empresa();
		empresaController = new EmpresaController(empresaDAO, result, validator);
	}

	@Test
	public void quandoChamaOMetodoListagemRetornaOMetodoGetTodasDoDAO()
			throws Exception {
		listaDeEmpresas = new ArrayList<Empresa>();
		listaDeEmpresas.add(empresa);

		when(empresaDAO.getTodas()).thenReturn(listaDeEmpresas);

		List<Empresa> listagem = empresaController.listagem();

		verify(empresaDAO).getTodas();
		verify(empresaDAO).contaQuantidadeDeRegistros();
		assertThat(listagem, is(listaDeEmpresas));
	}

	@Test
	public void quandoChamaOMetodoListagemComParametroDeBuscaRetornaOMetodoPesquisaDoDAO() throws Exception {
		listaDeEmpresas = new ArrayList<Empresa>();
		listaDeEmpresas.add(empresa);
		
		when(empresaDAO.pesquisa("teste")).thenReturn(listaDeEmpresas);		
		List<Empresa> busca = empresaController.listagem(listaDeEmpresas);
		
		assertThat(busca, is(listaDeEmpresas));
	}
	
	@Test
	public void quandoChamaOMetodoListagemChamaOIncludeDoResult() throws Exception {
		Long quantidadeRegistros = 5L;
		when(empresaDAO.contaQuantidadeDeRegistros()).thenReturn(
				quantidadeRegistros);

		empresaController.listagem();

		verify(result).include("totalDeRegistros", quantidadeRegistros);
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

		empresaController.cadastro(empresa);

		verify(empresaDAO).getById(empresa.getId());
	}

	@Test
	public void quandoChamaOMetodoCadastroIncluiEditarNoResult()
			throws Exception {
		empresa.setId(1);

		empresaController.cadastro(empresa);

		verify(result).include("editar", true);
	}

	@Test
	public void quandoChamaAPrimeiraPaginaRetornaOsVintePrimeirosRegistros()
			throws Exception {
		int pagina = 1;
		criaEPopulaListaCom100Empresas();

		when(empresaDAO.getTodasComPaginacao(pagina)).thenReturn(
				listaDeEmpresasPaginacao.subList(0, 20));

		List<Empresa> empresasDaPrimeiraPagina = empresaDAO
				.getTodasComPaginacao(pagina);
		int indiceUltimaEmpresa = (int) (empresasDaPrimeiraPagina.size() - 1);

		assertThat(empresasDaPrimeiraPagina.get(0).getNomeFantasia(),
				is("Empresa 1"));
		assertThat(empresasDaPrimeiraPagina.get(indiceUltimaEmpresa)
				.getNomeFantasia(), is("Empresa 20"));
	}

	@Test
	public void quandoChamaASegundaPaginaRetornaOsVinteSegundosRegistros() throws Exception {
		int pagina = 2;
		criaEPopulaListaCom100Empresas();
		when(empresaDAO.getTodasComPaginacao(pagina)).thenReturn(listaDeEmpresasPaginacao.subList(20, 40));

		List<Empresa> empresasDaSegundaPagina = empresaDAO.getTodasComPaginacao(pagina);
		
		int indiceUltimaEmpresa = (int) (empresasDaSegundaPagina.size() - 1);

		assertThat(empresasDaSegundaPagina.get(0).getNomeFantasia(),
				is("Empresa 21"));
		assertThat(empresasDaSegundaPagina.get(indiceUltimaEmpresa)
				.getNomeFantasia(), is("Empresa 40"));

	}
	
	private void criaEPopulaListaCom100Empresas() {
		listaDeEmpresasPaginacao = new ArrayList<Empresa>();
		for (int i = 1; i < 101; i++) {
			Empresa e = new Empresa();
			e.setNomeFantasia("Empresa " + i);
			listaDeEmpresasPaginacao.add(e);
		}
	}

}