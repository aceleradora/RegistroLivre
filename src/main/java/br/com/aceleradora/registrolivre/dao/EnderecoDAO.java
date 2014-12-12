package br.com.aceleradora.registrolivre.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.aceleradora.registrolivre.model.Endereco;
import br.com.caelum.vraptor.ioc.Component;

@Component
public class EnderecoDAO implements IEnderecoDAO{
	
	private Session sessao;

	public EnderecoDAO(Session sessao) {
		this.sessao = sessao;
	}
	
	@Override
	public void adiciona(Endereco endereco) {
		Transaction transicao = sessao.beginTransaction();
		sessao.save(endereco);
		transicao.commit();
	}

}
