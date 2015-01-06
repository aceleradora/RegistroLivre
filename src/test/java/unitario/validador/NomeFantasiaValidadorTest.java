package unitario.validador;

import static org.junit.Assert.assertThat;
import static unitario.validador.HibernateValidationUtils.falha;
import static unitario.validador.HibernateValidationUtils.noCampo;
import static unitario.validador.HibernateValidationUtils.sucesso;
import static unitario.validador.HibernateValidationUtils.validacaoPara;

import org.junit.Before;
import org.junit.Test;

import br.com.aceleradora.registrolivre.model.Empresa;

public class NomeFantasiaValidadorTest {

	private Empresa empresa;

	@Before
	public void setUp() {
		empresa = new Empresa();
	}

	@Test
	public void sucedeSeONomeFantasiaTiverMaisQueUmCaracter() {
		empresa.setNomeFantasia("aaa");

		assertThat(validacaoPara(empresa, noCampo("nomeFantasia")), sucesso());
	}

	@Test
	public void falhaSeONomeFantasiaTiverMenosQueDoisCaracteres() {
		empresa.setNomeFantasia("A");

		assertThat(validacaoPara(empresa, noCampo("nomeFantasia")), falha());
	}

	@Test
	public void falhaSeONomeFantasiaForNulo() {
		empresa.setNomeFantasia(null);

		assertThat(validacaoPara(empresa, noCampo("nomeFantasia")), falha());
	}
}
