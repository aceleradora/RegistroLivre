package br.com.aceleradora.RegistroLivre.model;

import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.caelum.vraptor.ioc.Component;

@Component
public class EntidadeDAO {

	private Session sessao;

	public EntidadeDAO(SessionCreator session) {
		this.sessao = session.getInstance();
	}

	public void adiciona(Entidade entidade) {
		Transaction tx = sessao.beginTransaction();
		sessao.save(entidade);
		tx.commit();
	}

}
