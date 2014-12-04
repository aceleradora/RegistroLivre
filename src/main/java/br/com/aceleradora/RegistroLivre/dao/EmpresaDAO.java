package br.com.aceleradora.RegistroLivre.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
		return sessao.createQuery("FROM Empresa ORDER BY dataregistro DESC")
				.list();
	}
	
	public List<Empresa> getTodasComPaginacao(int numeroDaPagina){
		int maximoDeRegistros = 20;
		
		Query query = sessao.createQuery("FROM Empresa ORDER BY dataregistro DESC");
		query.setFirstResult((numeroDaPagina -1) * maximoDeRegistros);
		query.setMaxResults(maximoDeRegistros);
		
		return query.list();
	}

	public Empresa getById(long id) {
		Empresa empresa = (Empresa) sessao.get(Empresa.class, id);

		return empresa;
	}

	public List<Empresa> pesquisa(String textoParaBusca) {
		textoParaBusca = textoParaBusca.toLowerCase();
		Date dataParaPesquisa = null;
		
		String sqlQuery = "SELECT DISTINCT empresa "
				+ "FROM Empresa AS empresa "
				+ "LEFT JOIN empresa.socios AS socio "
				+ "WHERE empresa.cnpj LIKE :busca "
				+ "OR lower(unaccent(empresa.nomeFantasia)) LIKE :busca "
				+ "OR lower(unaccent(empresa.razaoSocial)) LIKE :busca "
				+ "OR lower(unaccent(empresa.endereco.logradouro)) LIKE :busca "
				+ "OR lower(unaccent(empresa.endereco.cidade)) LIKE :busca "
				+ "OR lower(unaccent(empresa.endereco.uf)) LIKE :busca "
				+ "OR empresa.endereco.cep LIKE :busca "
				+ "OR lower(unaccent(socio.nome)) LIKE :busca "
				+ "OR socio.cpf LIKE :busca ";

		try {
			String textoParaBuscaData = textoParaBusca.replaceAll("-", "/");
			SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
			dataParaPesquisa = formatoData.parse(textoParaBuscaData);
			sqlQuery += "OR empresa.dataCriacaoEmpresa = :data ";
		} catch (ParseException e) {}

		Query query = sessao.createQuery(sqlQuery);

		query.setParameter("busca", "%" + textoParaBusca + "%");
		
		if (dataParaPesquisa != null) {
			query.setParameter("data", dataParaPesquisa);
		}

		return query.list();
	}

	public Long contaQuantidadeDeRegistros() {
		long quantidadeDeRegistros = (Long) sessao
				.createCriteria(Empresa.class)
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