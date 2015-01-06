package unitario.validador;

import static org.junit.Assert.assertThat;
import static unitario.validador.HibernateValidationUtils.*;

import org.junit.Before;
import org.junit.Test;

import br.com.aceleradora.registrolivre.model.Socio;

public class CPFValidadorTest {
	
	private Socio socio;
	
	@Before
	public void setUp(){
		socio = new Socio();
	}
		
	@Test
	public void sucedeSeCpfEValido(){
		socio.setCpf("083.533.674-32");
		
		assertThat(validacaoPara(socio, noCampo("cpf")), sucesso());
	}
	
	@Test	
	public void falhaSeCpfForInvalidoForLetras(){
		socio.setCpf("aaa");
		
		assertThat(validacaoPara(socio, noCampo("cpf")), falha());
	}
	
	@Test	
	public void falhaSeCpfForInvalido(){
		socio.setCpf("111.111.111-11");

		assertThat(validacaoPara(socio, noCampo("cpf")), falha());
	}

}
