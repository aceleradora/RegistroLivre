package unitario;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import br.com.aceleradora.registrolivre.dao.ConstrutorHQL;

public class ConstrutorHQLTest {
	
	@Test	
	public void deveGerarUmaQueryQueSelecioneTodosOsCamposDeTodasAsEmpresas(){
		String queryEsperada = "SELECT  DISTINCT empresa FROM Empresa AS empresa";		
		ConstrutorHQL hql = new ConstrutorHQL();
		
		hql.select(ConstrutorHQL.DISTINCT + "empresa");
		hql.from("Empresa");
		hql.as("empresa");
		
		assertThat(hql.toString(), is(queryEsperada));
	}
	
	@Test
	public void deveGerarUmObjetoQueryComUmParametroBusca(){
		String parametroEsperado = "busca";		
		ConstrutorHQL hql = new ConstrutorHQL();		
	}
}
