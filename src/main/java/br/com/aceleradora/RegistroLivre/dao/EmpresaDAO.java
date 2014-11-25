package br.com.aceleradora.RegistroLivre.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;

import br.com.aceleradora.RegistroLivre.model.Empresa;
import br.com.caelum.vraptor.ioc.Component;

@Component
public class EmpresaDAO implements IEmpresaDAO {

	private Session sessao;

	public EmpresaDAO(Session sessao) {
		this.sessao = sessao;
	}

	public List<Empresa> getTodas() {
		return sessao.createQuery("FROM Empresa").list();
	}

	public Empresa getById(long id) {
		Empresa empresa = (Empresa) sessao.get(Empresa.class, id);	
		
		return empresa;
	}
	
	public List<Empresa> pesquisaPorCnpj(String cnpj) {
		Query query = sessao.createQuery("FROM Empresa WHERE cnpj LIKE :cnpj");
		query.setParameter("cnpj", cnpj);
		return query.list();
	}

	public Long contaQuantidadeDeRegistros() {
		long quantidadeDeRegistros = (Long) sessao.createCriteria(Empresa.class)
				.setProjection(Projections.rowCount()).list().get(0);
		
		
		return quantidadeDeRegistros;
		
	}

	@Override
	public void adiciona(Empresa empresa) {
		Transaction transacao = sessao.beginTransaction();
		sessao.save(empresa);
		transacao.commit();
		
	}
	
	@Override
	public void atualiza(Empresa empresa) {
		Transaction transacao = sessao.beginTransaction();
		sessao.update(empresa);
		transacao.commit();
	}

	public List<Empresa> pesquisaPorNomeFantasia(String nomeFantasia) {
		return null;
	}
	
}