package funcional;

import static org.fest.assertions.Assertions.assertThat;

import org.fluentlenium.core.FluentPage;

public class PaginaInicial extends FluentPage{
	
	public String getUrl(){
		return "http://localhost:8080";
	}
	
	@Override
	public void isAt(){
		assertThat(title().contains("Registro Livre"));
	}
	
	public void preencheEEnviaFormDeBusca(String parametro) throws InterruptedException{
//		find("#btn-submit").first().getAttribute("disabled");
//		System.out.println(find("#btn-submit").first().getAttribute("disabled"));
		fill("#campoPesquisado").with(parametro);
		submit("#btn-submit");
		Thread.sleep(1000);
	}
	
	public void abreBuscaAvancada() throws InterruptedException {
		
		click("#abrir-busca-avancada");

		Thread.sleep(1000);
		
	}
	
	public boolean naoPreencheInputDaBuscaEClicaEmBuscar(String parametro){
		fill("#campoPesquisado").with(parametro);
		
		return find("#btn-submit").first().isEnabled();
	}
	
	
	public boolean preencheInputDaBuscaEClicaEmBuscar(String parametro) throws InterruptedException {
		fill("#campoPesquisado").with(parametro);
		
		submit("#btn-submit");
		
		return find("#btn-submit").first().isEnabled();
	}
	

}
