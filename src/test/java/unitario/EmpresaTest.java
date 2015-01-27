package unitario;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import br.com.aceleradora.registrolivre.model.Empresa;
import br.com.aceleradora.registrolivre.model.Socio;


public class EmpresaTest {

	Empresa empresa;
	
	@Before
	public void setup() {
		empresa = new Empresa();
	}
	
	@Test
	public void quandoMandarStringComPontoEtracosBarraRetornaStringSemPontoTraco() {
		String stringComPontoTracoBarra = "82.70/75..290-81";
		String stringSemPontoTracoBarra = "82707529081";

		String result = empresa
				.retiraPontosTracos(stringComPontoTracoBarra);

		assertThat(result, is(stringSemPontoTracoBarra));
	}
	
	@Test
	public void quandoChamarOMetodoRetiraPontosTracosBarrasCnpjECpfVaiRetirarOsPontosTracosBarrasCnpjDaEmpresaEDoCpfDosSocios(){
		Socio socio = new Socio();
		socio.setCpf("827.075.290-87");
		List<Socio> listaSocios = new ArrayList<Socio>();
		listaSocios.add(socio);
		empresa.setSocios(listaSocios);
		empresa.setCnpj("57.380.361/0001-28");
		String cnpjSemPontoBarraPonto = "57380361000128";
		String cpfSemPontoBarraPonto = "82707529087";
		
		empresa.retiraPontosTracosBarrasCnpjECpf();

		assertThat(empresa.getCnpj(), is(cnpjSemPontoBarraPonto));
		assertThat(empresa.getSocios().get(0).getCpf(), is(cpfSemPontoBarraPonto));
		
	}
	
	@Test
	public void quandoChamarOMetodoCnpjJaExistenteMandandoUmaListaVaiRetornarVerdadeSeOCnpjDaEmpresaExistirNaLista(){
		String cnpj = "56863298000118";
		empresa.setCnpj(cnpj);		
		List<String> listaDeCnpj = new ArrayList<String>();
		listaDeCnpj.add(cnpj);
		
		boolean result = empresa.cnpjJaExistente(listaDeCnpj);

		assertThat(result, is(true));		
	}
	
	@Test
	public void quandoChamarOMetodoCnpjJaExistenteMandandoUmaListaVaiRetornarFalsoSeOCnpjDaEmpresaNaoExistirNaLista(){
		String cnpj = "56863298000118";
		empresa.setCnpj("34792367000107");		
		List<String> listaDeCnpj = new ArrayList<String>();
		listaDeCnpj.add(cnpj);
		
		boolean result = empresa.cnpjJaExistente(listaDeCnpj);

		assertThat(result, is(false));		
	}
	
	@Test
	public void quandoChamarOMetodoTrazDadosDaEmpresaRetornaUmaStringComTodosOsDadosNaoVaziosDela(){
		String cnpj = "56863298000118";
		empresa.setCnpj(cnpj);
		String nomeFantasia = "Larah Instrumentos Musicais";
		empresa.setNomeFantasia(nomeFantasia);
		boolean antigo = true;
		
		String esperado = "Data do Registro Antigo: " + empresa.formataData(empresa.getDataRegistro()) + 
								"\n\nCNPJ: " + cnpj + "\nNome Fantasia: " + nomeFantasia ; 
		String result = empresa.trazDadosDaEmpresa(antigo);
	
		assertThat(result, is(esperado));
	}
}
