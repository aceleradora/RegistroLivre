package funcional;

import java.io.File;

import org.fluentlenium.adapter.FluentTest;
import org.fluentlenium.core.annotation.Page;
import org.junit.Ignore;
import org.junit.Test;

import static org.fest.assertions.Assertions.assertThat;
import static org.junit.Assert.*;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public class PaginaInicialTest extends FluentTest {
//	private static final String URL_PAGE = "http://localhost:8080";
	protected static String path;
	
	static {
		path = new File("").getAbsolutePath() + "/src/main/resources";
//		System.setProperty("webdriver.chrome.driver", path + "/chromedriver");
		System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");
	}

	@Page
	private PaginaInicial paginaInicial = new PaginaInicial();

	public WebDriver driver = new ChromeDriver(DesiredCapabilities.chrome());
	
	@Override
	public WebDriver getDefaultDriver() {
		return driver;
	}
	
	@Ignore
	@Test
	public void deveEntrarNaPagina() throws Exception {
		goTo(paginaInicial);
	}



	 @Test
	 public void deveBuscarEmpresaExistente() throws Exception {
		goTo(paginaInicial);
	 	paginaInicial.preencheEEnviaFormDeBusca("Larah");
	 	
	 	assertAt(paginaInicial);
	 
	 }
	

	 @Test
	 public void deveAbrirEABuscaAvancada() throws Exception {
	
		 goTo(paginaInicial);
	
		 paginaInicial.abreBuscaAvancada();

	 }
}
