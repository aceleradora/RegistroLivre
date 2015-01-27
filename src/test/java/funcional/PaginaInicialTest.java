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
		System.setProperty("webdriver.chrome.driver", path + "/chromedriver");
//		System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");
	}

	@Page
	private PaginaInicial paginaInicial = new PaginaInicial();

	public WebDriver driver = new ChromeDriver(DesiredCapabilities.chrome());
	
	@Override
	public WebDriver getDefaultDriver() {
		return driver;
	}
	
	
	 @Test
	 public void quandoOInputDaBuscaEstiverVazioOBotaoDaBuscaDeveEstarDesativado() throws Exception {
		goTo(paginaInicial);
		String parametro = "";
	 	
	 	boolean buttonIsEnabled = paginaInicial.preencheInputDaBusca("");
	 	
	 	assertEquals(buttonIsEnabled, false);
	 }

	 
	 @Test
	 public void quandoOInputDaBuscaEstiverPreenchidoOBotaoDeveEstarAtivado() throws Exception {
		goTo(paginaInicial);
		String parametro = "Larah";
		
		boolean buttonIsEnabled = paginaInicial.preencheInputDaBusca(parametro);
		
		assertEquals(buttonIsEnabled, true);
	 }
	
	 
	 @Test
	 public void quandoClicaNoLinkBuscaAvancadaDeveAbrirUmNovoFormDeBusca() throws Exception {
		 goTo(paginaInicial);
		 
		 boolean buscaAvancadaIsDisplayed = paginaInicial.abreBuscaAvancada(); 
		 
		 assertEquals(buscaAvancadaIsDisplayed, true);
	 }
	 
	 
	 @Test
	 public void quandoOUsuarioClicarEmPesquisaSocioDeveAbrirUmNovoFormDeSocio() throws InterruptedException{
		 goTo(paginaInicial);
		 
		 paginaInicial.clicaNoBotaoPesquisaSocio();
		 
		 assertTrue(pageSource().contains("Nome do sócio"));
	 }
}
