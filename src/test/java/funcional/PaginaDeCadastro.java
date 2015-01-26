package funcional;

import org.fluentlenium.core.FluentPage;

public class PaginaDeCadastro extends FluentPage{

	@Override
	public String getUrl() {
		return "http://registro-livre-staging.herokuapp.com";
	}
	
}
