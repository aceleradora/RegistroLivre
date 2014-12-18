package br.com.aceleradora.registrolivre.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;

import br.com.aceleradora.registrolivre.model.Empresa;
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

	public Empresa getById(long id) {
		Empresa empresa = (Empresa) sessao.get(Empresa.class, id);

		return empresa;
	}

	public List<Empresa> pesquisa(String textoParaBusca) {
		if (textoParaBusca == null) {
			return new ArrayList<Empresa>();
		}
		textoParaBusca = textoParaBusca.toLowerCase();
		Calendar dataParaPesquisa = Calendar.getInstance();

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

			dataParaPesquisa.setTime(formatoData.parse(textoParaBuscaData));
			sqlQuery += "OR empresa.dataCriacao = :data ";
		} catch (ParseException e) {
			dataParaPesquisa = null;
			textoParaBusca = textoParaBusca.replaceAll("[./-]", "");
		}

		sqlQuery += " ORDER BY empresa.dataRegistro DESC ";

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

	public void salva(Empresa empresa) {
		Transaction transacao = sessao.beginTransaction();
		sessao.saveOrUpdate(empresa);
		transacao.commit();
	}

	public List<String> getParaAutoCompletar(String textoDigitado) {
		List<String> retorno = new ArrayList<String>();

		retorno.addAll(pesquisaPorNomeFantasia(textoDigitado));
		retorno.addAll(pesquisaPorRazaoSocial(textoDigitado));
		retorno.addAll(pesquisaPorNomeDosSocios(textoDigitado));

		return retorno;
	}

	private List<String> pesquisaPorNomeDosSocios(String textoDigitado) {
		Query query = sessao
				.createQuery("SELECT socio.nome "
						+ " FROM Empresa AS empresa "
						+ " LEFT JOIN empresa.socios AS socio "
						+ " WHERE lower(unaccent(socio.nome)) LIKE lower(unaccent(:busca))");

		query.setParameter("busca", "%" + textoDigitado + "%");

		return query.list();
	}

	private List<String> pesquisaPorRazaoSocial(String textoDigitado) {
		Query query = sessao
				.createQuery("SELECT empresa.razaoSocial "
						+ " FROM Empresa AS empresa "
						+ " WHERE lower(unaccent(empresa.razaoSocial)) LIKE lower(unaccent(:busca)) ");

		query.setParameter("busca", "%" + textoDigitado + "%");

		return query.list();
	}

	private List<String> pesquisaPorNomeFantasia(String textoDigitado) {
		Query query = sessao
				.createQuery("SELECT empresa.nomeFantasia "
						+ " FROM Empresa AS empresa "
						+ " WHERE lower(unaccent(empresa.nomeFantasia)) LIKE lower(unaccent(:busca)) ");

		query.setParameter("busca", "%" + textoDigitado + "%");

		return query.list();
	}
}