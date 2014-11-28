package unitario;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import br.com.aceleradora.RegistroLivre.model.Empresa;
import br.com.aceleradora.RegistroLivre.model.Endereco;
import br.com.aceleradora.RegistroLivre.model.Socio;
import br.com.aceleradora.RegistroLivre.model.Validador;
import br.com.caelum.vraptor.interceptor.multipart.UploadedFile;

@RunWith(MockitoJUnitRunner.class)
public class ValidadorTest {

	@Mock UploadedFile arquivo;
	@Mock Empresa empresa;

	@Before
	public void setUp() {
		
	}

	@Test
	public void retornaVerdadeSeCpfEValido() throws Exception {
		boolean result = Validador.verificaCpf("071.549.456-21");

		assertTrue(result);
	}

	@Test
	public void retornaFalsoSeCpfForInvalido() throws Exception {
		boolean result = Validador.verificaCpf("111.111.111-11");

		assertFalse(result);
	}

	@Test
	public void retornaVerdadeSeCnpjEValido() throws Exception {
		boolean result = Validador.verificaCnpj("47960950/0449-27");

		assertTrue(result);
	}

	@Test
	public void retornaFalsoSeCnpjForInvalido() throws Exception {
		boolean result = Validador.verificaCnpj("11111111/1111-11");

		assertFalse(result);
	}

	@Test
	public void retornaFalsoSeAlgumSocioTivereCpfInvalido() {
		List<Socio> socios = new ArrayList<Socio>();
		Socio pessoa = new Socio("joao", "071.549.456-21", true);
		Socio pessoa2 = new Socio("maria", "111.111.111-11", true);
		socios.add(pessoa);
		socios.add(pessoa2);

		boolean result = Validador.verificaCpfListaSocio(socios);

		assertFalse(result);
	}

	@Test
	public void retornaVerdadeSeTodosOsSociosTiveremCpfValidoOuSeOSocioNaoTiverCpf() {
		List<Socio> socios = new ArrayList<Socio>();
		Socio pessoa = new Socio("joao", null, true);
		Socio pessoa2 = new Socio("maria", "848.817.120-04", true);
		socios.add(pessoa);
		socios.add(pessoa2);

		boolean result = Validador.verificaCpfListaSocio(socios);

		assertTrue(result);
	}
	
	@Test
	public void retornaVerdadeSeTodosOsSociosTiveremCpfValido(){
		List<Socio> socios = new ArrayList<Socio>();
		Socio pessoa = new Socio("joao", "071.549.456-21", true);
		Socio pessoa2 = new Socio("maria", "848.817.120-04", true);
		socios.add(pessoa);
		socios.add(pessoa2);
		
		boolean result = Validador.verificaCpfListaSocio(socios);
		
		assertTrue(result);		
	}
	
	@Test
	public void retornaVerdadeSeONomeFantasiaTiverMaisQueUmCaracter(){
		boolean result = Validador.verificaNomeFantasia("aaa");
		
		assertTrue(result);		
	}
	
	@Test
	public void retornaFalsoSeONomeFantasiaTiverMenosQueDoisCaracter(){
		boolean result = Validador.verificaNomeFantasia("a");
		
		assertFalse(result);		
	}
	
	@Test
	public void retornaFalsoSeONomeFantasiaForNulo(){
		boolean result = Validador.verificaNomeFantasia(null);
		
		assertFalse(result);
	}

	

	@Test
	public void retornaVerdadeSeONumeroDoEnderecoForNumero(){
		Endereco endereco = new Endereco();
		endereco.setNumero("555");
		when(empresa.getEndereco()).thenReturn(endereco);
		
		boolean result = Validador.verificaNumeroEndereco(empresa);
		
		assertTrue(result);
	}

	@Test
	public void retornaFalsoSeONumeroDoEnderecoNaoForNumero(){
		Endereco endereco = new Endereco();
		endereco.setNumero("sss");
		when(empresa.getEndereco()).thenReturn(endereco);
		
		boolean result = Validador.verificaNumeroEndereco(empresa);
		
		assertFalse(result);
	}

	@Test
	public void retornaVerdadeSeONumeroDoEnderecoForNull(){
		when(empresa.getEndereco()).thenReturn(null);
		
		boolean result = Validador.verificaNumeroEndereco(empresa);
		
		assertTrue(result);
	}
	
	@Test
	public void retornaVerdadeSeContemPDFNoFinalDaString(){
		when(arquivo.getFileName()).thenReturn("blablabla.pdf");
		
		boolean result = Validador.verificaExtensaoArquivo(arquivo);
		
		assertTrue(result);
	}
	
	@Test
	public void retornaFalseSeNaoContemPDFNoFinalDaString(){
		when(arquivo.getFileName()).thenReturn("blablabla.pdf.jpg");
		boolean result = Validador.verificaExtensaoArquivo(arquivo);
		
		assertFalse(result);
	}
	
	@Test
	public void retornaFalseSeStringForNull(){
		boolean result = Validador.verificaExtensaoArquivo(null);
		
		assertFalse(result);
	}
}
