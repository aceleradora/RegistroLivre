package funcional;

import java.util.concurrent.TimeUnit;

import org.fluentlenium.core.FluentPage;

import static org.fest.assertions.Assertions.assertThat;

public class PaginaInicial extends FluentPage{
	
	public String getUrl(){
		return "http://localhost:8080";
	}
	
	@Override
	public void isAt(){
		assertThat(title().contains("Registro Livre"));
	}
	
	public void preencheEEnviaFormDeBusca(String parametro){
		fill("#campoPesquisado").with(parametro);
		submit("#btn-submit");
	}
	
	public void abreBuscaAvancada() {
		
		click("#busca-avancada-link-ancora");

		find("#busca-avancada").getAttributes("style");
	}

}
