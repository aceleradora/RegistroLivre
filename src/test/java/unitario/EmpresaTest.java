package unitario;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import br.com.aceleradora.RegistroLivre.model.Empresa;

public class EmpresaTest {

	private Empresa empresa;

	@Before
	public void setup() {
		empresa = new Empresa();
	}

	@Test
	public void retornaStringVaziaQuandoADataENula() throws Exception {

		empresa.setDataCriacao(null);

		assertThat(empresa.getDataCriacao(), is(""));
	}

	@Test
	public void retornaStringNoFormatoDiaMesAnoQuandoDataEstaCorreta()
			throws Exception {

		empresa.setDataCriacao("17/11/2004");

		assertThat(empresa.getDataCriacao(), is("17/11/2004"));
	}

	@Test
	public void retornaStringVaziaQuandoMesEDiaEstiveremInvertidos()
			throws Exception {

		empresa.setDataCriacao("12/30/2013");

		assertThat(empresa.getDataCriacao(), is(""));
	}

	@Test
	public void retornaStringVaziaQuandoAnoVemAntesNaData() throws Exception {

		empresa.setDataCriacao("2014/10/10");

		assertThat(empresa.getDataCriacao(), is(""));
	}

	@Test
	public void retornaStringVaziaQuandoUsaHifenAoInvesDeBarra()
			throws Exception {

		empresa.setDataCriacao("27-11-2014");

		assertThat(empresa.getDataCriacao(), is(""));
	}
}
