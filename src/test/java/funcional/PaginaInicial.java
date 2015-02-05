package funcional;

import static org.fest.assertions.Assertions.assertThat;

import org.fluentlenium.core.FluentPage;

public class PaginaInicial extends FluentPage{
	
	public String getUrl(){
//		return "http://localhost:8080";
		return "http://registro-livre-staging.herokuapp.com";
	}
	
	@Override
	public void isAt(){
		assertThat(title().contains("Registro Livre"));
	}
	

	public boolean abreBuscaAvancada() throws InterruptedException {
		click("#abrir-busca-avancada");
		
		Thread.sleep(1000);

		return find("#busca-avancada").first().isDisplayed(); 
	}
	
	public boolean fechaBuscaAvancada() throws InterruptedException{

		click("#fecha-busca-avancada");
		
		Thread.sleep(1000);
		
		return find("#busca-avancada").first().isDisplayed();				
				
	}
	
	
	public boolean preencheInputDaBusca(String parametro) throws InterruptedException {
		fill("#campo-pesquisado").with(parametro);
		
		return find("#btn-submit").first().isEnabled();
	}
	
	
	public void clicaNoBotaoPesquisaSocio() throws InterruptedException{
		abreBuscaAvancada();
		
		click("#adiciona-socios-busca-avancada");
	}

}
