package br.com.aceleradora.RegistroLivre.dao;


import javax.annotation.PreDestroy;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

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
		configuration.setProperty("hibernate.connection.username", System.getenv("DB_CONNECTION_USUARIO"));
		configuration.setProperty("hibernate.connection.password", System.getenv("DB_CONNECTION_SENHA"));
		
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
