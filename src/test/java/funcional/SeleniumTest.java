package funcional;

import static org.fest.assertions.Assertions.assertThat;

import java.io.File;

import org.fluentlenium.adapter.FluentTest;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class SeleniumTest extends FluentTest {
	private static final String URL_PAGE = "http://registro-livre-staging.herokuapp.com";
	
	protected static String path;
	
	public static WebDriver driver = new HtmlUnitDriver();
	
	@Override
	public WebDriver getDefaultDriver() {
		return driver;
	}
	@Test
	public void deveTestarOIndex(){
		
		goTo(URL_PAGE);
		 
		assertThat(title()).contains("Registro Livre");
	}
	
	
}