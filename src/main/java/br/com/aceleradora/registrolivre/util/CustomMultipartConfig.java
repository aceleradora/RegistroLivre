package br.com.aceleradora.registrolivre.util;

import br.com.caelum.vraptor.interceptor.multipart.DefaultMultipartConfig;
import br.com.caelum.vraptor.ioc.ApplicationScoped;
import br.com.caelum.vraptor.ioc.Component;


@Component
@ApplicationScoped
public class CustomMultipartConfig extends DefaultMultipartConfig {

    public long getSizeLimit() {
        return 5 * 1024 * 1024; // 5MB
    }

}
