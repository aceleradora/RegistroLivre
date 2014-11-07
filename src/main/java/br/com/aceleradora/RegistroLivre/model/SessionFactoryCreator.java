package br.com.aceleradora.RegistroLivre.model;

import javax.annotation.PostConstruct;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;

import br.com.caelum.vraptor.ioc.ApplicationScoped;
import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.ComponentFactory;

@Component
//@ApplicationScoped
public class SessionFactoryCreator implements ComponentFactory<SessionFactory> {

	private SessionFactory sessionFactory;

	public SessionFactoryCreator() {
		create();
	}
	
//	@PostConstruct
	public void create() {
		AnnotationConfiguration configuration = new AnnotationConfiguration();
		
		configuration.setProperty("hibernate.connection.url", System.getenv("DB_URL_CONNECTION"));
		
		configuration.configure();
		
		sessionFactory = configuration.buildSessionFactory();
	}

	public void destroy() {
		sessionFactory.close();
	}

	@Override
	public SessionFactory getInstance() {
		return sessionFactory;
	}

}
