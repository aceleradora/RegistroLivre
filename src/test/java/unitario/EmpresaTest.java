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
	public void retornaStringVaziaQuandoADataCriacaoENula() throws Exception {
		empresa.setDataCriacao(null);
		assertThat(empresa.getDataCriacao(), is(""));
	}

	@Test
	public void retornaStringNoFormatoDiaMesAnoQuandoDataCriacaoEstaCorreta()
			throws Exception {
		empresa.setDataCriacao("17/11/2004");
		assertThat(empresa.getDataCriacao(), is("17/11/2004"));
	}

	@Test
	public void retornaStringVaziaQuandoMesEDiaEstiveremInvertidosNaDataCriacao()
			throws Exception {
		empresa.setDataCriacao("12/30/2013");
		assertThat(empresa.getDataCriacao(), is(""));
	}

	@Test
	public void retornaStringVaziaQuandoAnoVemAntesNaDataCriacao() throws Exception {
		empresa.setDataCriacao("2014/10/10");
		assertThat(empresa.getDataCriacao(), is(""));
	}

	@Test
	public void retornaStringVaziaQuandoUsaHifenAoInvesDeBarraNaDataCriacao()
			throws Exception {
		empresa.setDataCriacao("27-11-2014");
		assertThat(empresa.getDataCriacao(), is(""));
	}

	
	@Test
	public void retornaStringVaziaQuandoADataEmissaoDocumentoENula() throws Exception {
		empresa.setDataEmissaoDocumento(null);
		assertThat(empresa.getDataEmissaoDocumento(), is(""));
	}

	@Test
	public void retornaStringNoFormatoDiaMesAnoQuandoDataEmissaoDocumentoEstaCorreta()
			throws Exception {
		empresa.setDataEmissaoDocumento("17/11/2004");
		assertThat(empresa.getDataEmissaoDocumento(), is("17/11/2004"));
	}

	@Test
	public void retornaStringVaziaQuandoMesEDiaEstiveremInvertidosNaDataEmissaoDocumento()
			throws Exception {
		empresa.setDataEmissaoDocumento("12/30/2013");
		assertThat(empresa.getDataEmissaoDocumento(), is(""));
	}

	@Test
	public void retornaStringVaziaQuandoAnoVemAntesNaDataEmissaoDocumento() throws Exception {
		empresa.setDataEmissaoDocumento("2014/10/10");
		assertThat(empresa.getDataEmissaoDocumento(), is(""));
	}

	@Test
	public void retornaStringVaziaQuandoUsaHifenAoInvesDeBarraNaDataEmissaoDocumento()
			throws Exception {
		empresa.setDataEmissaoDocumento("27-11-2014");
		assertThat(empresa.getDataEmissaoDocumento(), is(""));
	} 
	
	
	@Test
	public void retornaADataDeRegistroAoCriarUmaNovaEmpresa(){		
		assertThat(empresa.getDataRegistro(), is("04/12/2014"));
	}
	
}
