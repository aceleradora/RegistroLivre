package br.com.aceleradora.RegistroLivre.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.aceleradora.RegistroLivre.model.Socio;
import br.com.caelum.vraptor.ioc.Component;

@Component
public class SocioDAO implements ISocioDAO {

	private Session sessao;
	
	public SocioDAO(Session sessao) {
		this.sessao = sessao;
	}
	
	@Override
	public void adiciona(Socio socio) {
		Transaction transicao = sessao.beginTransaction();
		sessao.save(socio);
		transicao.commit();
	}
}
