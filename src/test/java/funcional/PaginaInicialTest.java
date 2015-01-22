package funcional;

import org.fluentlenium.adapter.FluentTest;
import org.fluentlenium.core.annotation.Page;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class PaginaInicialTest extends FluentTest{

	
	public  WebDriver driver = new HtmlUnitDriver();
	
	
	@Page
	public PaginaInicial paginaInicial;
	
	
	@Override
	public WebDriver getDefaultDriver() {
		return driver;
	}
	
	@Test
	public void testaSePaginaExiste() throws Exception {
		goTo(paginaInicial);
	}
	
	
	@Ignore //Não conseguimos testar devido ao onKeyUp do botão. Só conseguimos tirando o disabled=false no HTML
	@Test
	public void deveBuscarEmpresaExistente() throws Exception {
		goTo(paginaInicial);
		paginaInicial.preencheEEnviaFormDeBusca("Larah");
	}

	@Ignore
	@Test
	public void deveAbrirABuscaAvancada() throws Exception {
		
		goTo(paginaInicial);
		
		paginaInicial.abreBuscaAvancada();
	}
	
	
	
	
}
