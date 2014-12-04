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
		return sessao.createQuery("FROM Empresa ORDER BY dataregistro DESC").list();
	}

	public Empresa getById(long id) {
		Empresa empresa = (Empresa) sessao.get(Empresa.class, id);	
		
		return empresa;
	}
	
	public List<Empresa> pesquisa(String busca) {
		busca = busca.toLowerCase();
		
		Query query = sessao.createQuery("SELECT DISTINCT empresa "
										+ "FROM Empresa AS empresa "
										+ "LEFT JOIN empresa.socios AS socio "
										+ "WHERE empresa.cnpj LIKE :busca "
											+ "OR lower(empresa.nomeFantasia) LIKE :busca "
											+ "OR lower(empresa.razaoSocial) LIKE :busca "
											+ "OR lower(empresa.endereco.logradouro) LIKE :busca "
											+ "OR lower(empresa.endereco.cidade) LIKE :busca "
											+ "OR lower(empresa.endereco.uf) LIKE :busca "
											+ "OR empresa.endereco.cep LIKE :busca "
											+ "OR lower(socio.nome) LIKE :busca "
											+ "OR socio.cpf LIKE :busca ");

		query.setParameter("busca", "%" + busca + "%");
		
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