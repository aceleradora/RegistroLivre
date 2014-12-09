package funcional;

import static org.fest.assertions.Assertions.assertThat;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.fluentlenium.adapter.FluentTest;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import br.com.caelum.vraptor.restfulie.relation.RelationBuilder.WithName;

public class BuscaTest extends FluentTest{

	private static final String URL_PAGE = "http://registro-livre-staging.herokuapp.com";
	
	public static WebDriver driver = new HtmlUnitDriver();
	
	@Override
	public WebDriver getDefaultDriver() {
		return driver;
	}
	
	@Test
	public void quandoClicaNoBotãoSemEnviarNadaDeveRetornarErroParaPáginaPrincipal(){
		
		goTo(URL_PAGE);
		 
		find("#btn-submit").click();
		
		assertThat(find(".alert").getText(), containsString("Não há nenhum registro de empresa para a busca efetuada."));
	
	}
		
	
}
