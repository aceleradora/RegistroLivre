package br.com.aceleradora.RegistroLivre.dao;

import javax.annotation.PreDestroy;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import br.com.caelum.vraptor.ioc.ApplicationScoped;
import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.ComponentFactory;


@Component
@ApplicationScoped
public class SessionFactoryCreator implements ComponentFactory<SessionFactory> {

	private SessionFactory sessionFactory;

	public SessionFactoryCreator() {
		create();
	}

	public void create() {
		Configuration configuration = new Configuration();
		
		configuration.setProperty("hibernate.connection.url", System.getenv("DB_CONNECTION_URL"));
		configuration.setProperty("hibernate.connection.username", System.getenv("DB_CONNECTION_USUARIO"));
		configuration.setProperty("hibernate.connection.password", System.getenv("DB_CONNECTION_SENHA"));
		
		configuration.configure();
		
		sessionFactory = configuration.buildSessionFactory();
	}
	
	@PreDestroy
	public void destroy() {		
		sessionFactory.close();
		System.out.println("SESSION FACTORY CREATOR DESTROY");
	}

	@Override
	public SessionFactory getInstance() {
		return sessionFactory;
	}

}
