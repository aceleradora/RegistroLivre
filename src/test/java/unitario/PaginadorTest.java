package unitario;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import br.com.aceleradora.RegistroLivre.model.Empresa;
import br.com.aceleradora.RegistroLivre.model.Paginador;

public class PaginadorTest {

	private Paginador paginador;
	
	@Before
	public void setUp() throws Exception {
		paginador = new Paginador();
	}

	@Test
	public void retornaUmaListaVaziaQuandoListaDeEmpresasForNula() {
		List<Empresa> result = paginador.getPagina(1);
		
		assertTrue(result.isEmpty());
	}

	@Test
	public void retornaUmaListaCom20PosicoesQuandoListaDeEmpresasTiver40posicoes() {
		List<Empresa> listaEmpresas = new ArrayList<Empresa>();
		populaLista(listaEmpresas, 40);
		
		paginador.setListaEmpresas(listaEmpresas);		
		
		List<Empresa> result = paginador.getPagina(1);
		
		assertThat(result.size(), is(20));
	}

	@Test
	public void retornaAListaQuandoElaTiverMenosDe20Elementos() {
		List<Empresa> listaEmpresas = new ArrayList<Empresa>();
		populaLista(listaEmpresas, 10);
		
		paginador.setListaEmpresas(listaEmpresas);		
		
		List<Empresa> result = paginador.getPagina(1);
		
		assertThat(result, is(listaEmpresas));
	}

	@Test
	public void retornaUmaListaCom3ElementosNaSegundaPaginaQuandoMandarUmaListaCom23Elementos() {
		List<Empresa> listaEmpresas = new ArrayList<Empresa>();
		populaLista(listaEmpresas, 23);
		
		paginador.setListaEmpresas(listaEmpresas);		
		
		List<Empresa> result = paginador.getPagina(2);
		
		assertThat(result.size(), is(3));
	}
	
	@Test
	public void retornaUmaListaVaziaNaSegundaPaginaQuandoListaMandadaPossuirMenosDeVinteElementos(){
		List<Empresa> listaEmpresas = new ArrayList<Empresa>();
		populaLista(listaEmpresas, 3);
		
		paginador.setListaEmpresas(listaEmpresas);		
		
		List<Empresa> result = paginador.getPagina(2);
		
		assertThat(result.size(), is(0));
	}

	private void populaLista(List<Empresa> listaEmpresas, int tamanho) {
		Empresa empresa = new Empresa();
		for (int i = 0; i < tamanho; i++) {
			listaEmpresas.add(empresa);
		}
	}

}
