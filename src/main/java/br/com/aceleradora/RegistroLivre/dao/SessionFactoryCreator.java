package br.com.aceleradora.RegistroLivre.dao;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;

import br.com.caelum.vraptor.ioc.ApplicationScoped;
import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.ComponentFactory;

@Component
public class SessionFactoryCreator implements ComponentFactory<SessionFactory> {

	private SessionFactory sessionFactory;

	public SessionFactoryCreator() {
		create();
	}

	public void create() {
		AnnotationConfiguration configuration = new AnnotationConfiguration();
		
		configuration.setProperty("hibernate.connection.url", System.getenv("DB_CONNECTION_URL"));
		
		configuration.configure();
		
		sessionFactory = configuration.buildSessionFactory();
	}
	
	@PreDestroy
	public void destroy() {
		sessionFactory.close();
	}

	@Override
	public SessionFactory getInstance() {
		return sessionFactory;
	}

}
