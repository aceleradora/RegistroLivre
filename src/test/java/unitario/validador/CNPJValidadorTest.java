package unitario.validador;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import static unitario.validador.HibernateValidationUtils.*;

import br.com.aceleradora.registrolivre.model.Empresa;

public class CNPJValidadorTest {
	public Empresa empresa;

	@Before
	public void setup() {
		empresa = new Empresa();
	}

	@Test
	public void sucessoSeOCnpjEValido() {
		empresa.setCnpj("47960950/0449-27");
		
		assertThat(validacaoPara(empresa, noCampo("cnpj")), sucesso());
	}

	@Test
	public void falhaSeOCnpjForInvalido() {
		empresa.setCnpj("11111111/1111-11");
		
		assertThat(validacaoPara(empresa, noCampo("cnpj")), falha());
	}

	@Test
	public void falhaSeOCnpjForLetras() {
		empresa.setCnpj("aaaa");
		
		assertThat(validacaoPara(empresa, noCampo("cnpj")), falha());
	}

	@Test
	public void falhaSeOCnpjForNulo() {
		empresa.setCnpj(null);
		
		assertThat(validacaoPara(empresa, noCampo("cnpj")), falha());
	}
}
