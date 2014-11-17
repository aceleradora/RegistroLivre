package funcional;

import java.io.File;

import static org.fest.assertions.Assertions.assertThat;
import static org.junit.Assert.*;

import org.fluentlenium.adapter.FluentTest;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public class SeleniumTest extends FluentTest {
	private static final String URL_PAGE = "http://registro-livre-aceleradora.herokuapp.com";
	
	protected static String path;
		
	static {
		path = new File("").getAbsolutePath()+"/src/main/resources";
		System.setProperty("webdriver.chrome.driver", path+"/chromedriver");
	}
	
	public static WebDriver driver = new HtmlUnitDriver();
	
	@Override
	public WebDriver getDefaultDriver() {
		return driver;
	}
	@Ignore
	@Test
	public void deveTestarOIndex(){
		
		goTo(URL_PAGE);
		 
		assertThat(title()).contains("Registro Livre");
	}
	
	
}