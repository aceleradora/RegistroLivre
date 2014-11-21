package unitario;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.meta.When;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import static org.mockito.Mockito.when;

import br.com.aceleradora.RegistroLivre.model.Socio;
import br.com.aceleradora.RegistroLivre.model.Validador;
import br.com.caelum.vraptor.interceptor.multipart.UploadedFile;

@RunWith(MockitoJUnitRunner.class)
public class ValidadorTest {
	
	@Mock UploadedFile arquivo;

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
	public void retornaVerdadeSeONomeFantasiaTiverDoisCaracteres(){
		boolean result = Validador.verificaNomeFantasia("aa");
		
		assertTrue(result);		
	}
	
	@Test
	public void retornaUmaListaComDoisObjetosQuandoMandarUmaListaDeSociosComDoisObjetosValidosETresNulos(){
		List<Socio> socios = new ArrayList<Socio>();
		Socio pessoa = new Socio("nome", "071.549.456-21", true);	
		Socio pessoa2 = new Socio(null, null, true);
		Socio pessoa3 = new Socio ("nome", "848.817.120-04", true);
		Socio pessoa4 = new Socio(null, null, true);
		Socio pessoa5 = new Socio(null, null, true);
		
		socios.add(pessoa);
		socios.add(pessoa2);
		socios.add(pessoa3);
		socios.add(pessoa4);
		socios.add(pessoa5);

		List<Socio> result = Validador.retiraSociosNulos(socios);
				
		assertEquals(result.size(), 2);
	}

	@Test
	public void retornaVerdadeSeONumeroDoEnderecoForNumero(){
		boolean result = Validador.verificaNumeroEndereco("555");
		
		assertTrue(result);
	}

	@Test
	public void retornaFalsoSeONumeroDoEnderecoNaoForNumero(){
		boolean result = Validador.verificaNumeroEndereco("sss");
		
		assertFalse(result);
	}

	@Test
	public void retornaVerdadeSeONumeroDoEnderecoForNull(){
		boolean result = Validador.verificaNumeroEndereco(null);
		
		assertTrue(result);
	}
	
	@Test
	public void retornaVerdadeSeContemPDFNaString(){
		when(arquivo.getFileName()).thenReturn("blablabla.pdf");
		
		boolean result = Validador.verificaExtensaoArquivo(arquivo);
		
		assertTrue(result);
	}
	
	@Test
	public void retornaFalseSeNaoContemPDFNaString(){
		when(arquivo.getFileName()).thenReturn("blablabla");
		boolean result = Validador.verificaExtensaoArquivo(arquivo);
		
		assertFalse(result);
	}
	
	@Test
	public void retornaFalseSeStringForNull(){
		boolean result = Validador.verificaExtensaoArquivo(null);
		
		assertFalse(result);
	}
	
	@Test
	public void retornaVerdadeSeOtamanhoDoArquivoForMenorQue2MB(){
		when(arquivo.getSize()).thenReturn(1000000L);
		
		boolean result = Validador.verificaTamanhoArquivo(arquivo);
		
		assertTrue(result);
	}
	
	@Test
	public void retornaFalsoQuandoOTamanhoDoArquivoEMaiorQue2MB() throws Exception {
		when(arquivo.getSize()).thenReturn(3000000L);
		
		boolean result = Validador.verificaTamanhoArquivo(arquivo);
		
		assertThat(result, is(false));
	}
	
	@Test
	public void retornaFalsoQuandoTamanhoDoArquivoEhIgualA2MB() throws Exception {
		when(arquivo.getSize()).thenReturn(2000000L);
		
		boolean result = Validador.verificaTamanhoArquivo(arquivo);
		
		assertThat(result, is(false));
	}
	
	@Test
	
	public void retornaFalsoQuandoOTamanhoDoArquivoEhNulo() throws Exception {
		boolean result = Validador.verificaTamanhoArquivo(null);
		
		
	}
}
