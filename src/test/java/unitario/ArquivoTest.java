package unitario;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.io.InputStream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import br.com.aceleradora.RegistroLivre.model.Empresa;
import br.com.aceleradora.RegistroLivre.util.Arquivo;

@RunWith(MockitoJUnitRunner.class)
public class ArquivoTest {

	@Mock
	InputStream inputStream;

	@Test
	public void quandoChamarOMetodoFormataNomeArquivoRetornaStringComNomeFantasiaDataEHoraDaEmpresaEnviada()
			throws IOException {
		Empresa empresa = new Empresa();
		empresa.setNomeFantasia("test");

		when(inputStream.read(new byte[1024])).thenReturn(-1);
		Arquivo arquivo = new Arquivo(inputStream, empresa);

		String result = arquivo.getNomeArquivo();
		String nomeArquivo = empresa.getNomeFantasia() + "_"
				+ "dd_MM_yyyy_HH:mm:ss";

		assertThat(result.length(), is(nomeArquivo.length()));
	}

	@Test
	public void quandoChamarOMetodoNormalizarNomeRetornaStringComNomeSemTracosEBarras()
			throws IOException {
		Empresa empresa = new Empresa();
		String nome = "teste";
		empresa.setNomeFantasia(nome + "/-.");

		when(inputStream.read(new byte[1024])).thenReturn(-1);
		Arquivo arquivo = new Arquivo(inputStream, empresa);

		String result = arquivo.getNomeArquivo();
		String nomeArquivo = nome + "_dd/MM/yyyy/HH-mm-ss";

		assertThat(result.length(), is(nomeArquivo.length()));
	}

}
