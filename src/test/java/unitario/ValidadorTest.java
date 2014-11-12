package unitario;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import br.com.aceleradora.RegistroLivre.model.Socio;
import br.com.aceleradora.RegistroLivre.model.Validador;

public class ValidadorTest {

	Validador validador;

	@Before
	public void setUp() {
		validador = new Validador();
	}

	@Test
	public void retornaVerdadeSeCpfEValido() throws Exception {
		boolean result = validador.verificaCpf("071.549.456-21");

		assertTrue(result);
	}

	@Test
	public void retornaFalsoSeCpfForInvalido() throws Exception {
		boolean result = validador.verificaCpf("111.111.111-11");

		assertFalse(result);
	}

	@Test
	public void retornaVerdadeSeCnpjEValido() throws Exception {
		boolean result = validador.verificaCnpj("47960950/0449-27");

		assertTrue(result);
	}

	@Test
	public void retornaFalsoSeCnpjForInvalido() throws Exception {
		boolean result = validador.verificaCnpj("11111111/1111-11");

		assertFalse(result);
	}

	@Test
	public void retornaVerdadeSeTodosOsSociosTiveremCpfValido() {
		List<Socio> socios = new ArrayList<Socio>();
		Socio pessoa = new Socio("joao", "071.549.456-21", true);
		Socio pessoa2 = new Socio("maria", "848.817.120-04", true);
		socios.add(pessoa);
		socios.add(pessoa2);

		boolean result = validador.verificaCpfListaSocio(socios);

		assertTrue(result);

	}

	@Test
	public void retornaFalsoSeAlgumSocioTivereCpfInvalido() {
		List<Socio> socios = new ArrayList<Socio>();
		Socio pessoa = new Socio("joao", "071.549.456-21", true);
		Socio pessoa2 = new Socio("maria", "111.111.111-11", true);
		socios.add(pessoa);
		socios.add(pessoa2);

		boolean result = validador.verificaCpfListaSocio(socios);

		assertFalse(result);

	}

	@Test
	public void retornaVerdadeSeTodosOsSociosTiveremCpfValidoOuSeOSocioNaoTiverCpf() {
		List<Socio> socios = new ArrayList<Socio>();
		Socio pessoa = new Socio("joao", null, true);
		Socio pessoa2 = new Socio("maria", "848.817.120-04", true);
		socios.add(pessoa);
		socios.add(pessoa2);

		boolean result = validador.verificaCpfListaSocio(socios);

		assertTrue(result);

	}

}
