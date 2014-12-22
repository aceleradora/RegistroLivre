package funcional;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertThat;

import org.fluentlenium.adapter.FluentTest;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class BuscaTest extends FluentTest{

	private static final String URL_PAGE = "http://registro-livre-staging.herokuapp.com";
	
	public static WebDriver driver = new HtmlUnitDriver();
	
	@Override
	public WebDriver getDefaultDriver() {
		return driver;
	}
	
	@Ignore
	@Test
	public void quandoClicaNoBotãoSemEnviarNadaDeveRetornarErroParaPáginaPrincipal(){
		
		goTo(URL_PAGE);

		find("#btn-submit").click();
		
		assertThat(find(".alert").getText(), containsString("Não há nada digitado no campo de busca, por favor tente novamente."));
	
	}		
	
}
