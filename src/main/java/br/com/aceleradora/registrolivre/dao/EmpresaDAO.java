package br.com.aceleradora.registrolivre.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;

import br.com.aceleradora.registrolivre.model.Empresa;
import br.com.caelum.vraptor.ioc.Component;

@Component
public class EmpresaDAO implements IEmpresaDAO{

	private Session sessao;

	public EmpresaDAO(Session sessao) {
		this.sessao = sessao;
	}

	@Override
	public List<Empresa> getTodas() {
		return sessao.createQuery("FROM Empresa ORDER BY dataregistro DESC")
				.list();
	}

	@Override
	public Empresa getById(long id) {
		Empresa empresa = (Empresa) sessao.get(Empresa.class, id);

		return empresa;
	}

	@Override
	public List<Empresa> pesquisa(String textoParaBusca) {
		if (textoParaBusca == null) {
			return new ArrayList<Empresa>();
		}
		Calendar dataParaPesquisa = Calendar.getInstance();

		String sqlQuery = "SELECT DISTINCT empresa "
				+ "FROM Empresa AS empresa "
				+ "LEFT JOIN empresa.socios AS socio "
				+ "WHERE empresa.cnpj LIKE :busca "
				+ "OR lower(unaccent(empresa.nomeFantasia)) LIKE lower(unaccent(:busca)) "
				+ "OR lower(unaccent(empresa.razaoSocial)) LIKE lower(unaccent(:busca)) "
				+ "OR lower(unaccent(empresa.endereco.logradouro)) LIKE lower(unaccent(:busca)) "
				+ "OR lower(unaccent(empresa.endereco.cidade)) LIKE lower(unaccent(:busca)) "
				+ "OR lower(unaccent(empresa.endereco.uf)) LIKE lower(unaccent(:busca)) "
				+ "OR empresa.endereco.cep LIKE :busca "
				+ "OR lower(unaccent(socio.nome)) LIKE lower(unaccent(:busca)) "
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

	@Override
	public Long contaQuantidadeDeRegistros() {
		long quantidadeDeRegistros = (Long) sessao
				.createCriteria(Empresa.class)
				.setProjection(Projections.rowCount()).list().get(0);

		return quantidadeDeRegistros;

	}

	@Override
	public void salva(Empresa empresa) {
		Transaction transacao = sessao.beginTransaction();
		sessao.saveOrUpdate(empresa);
		transacao.commit();
	}

	@Override
	public List<String> getParaAutoCompletar(String textoDigitado) {
		List<String> retorno = new ArrayList<String>();

		retorno.addAll(pesquisaPorCampo(textoDigitado, "nomeFantasia"));
		retorno.addAll(pesquisaPorCampo(textoDigitado, "razaoSocial"));
		retorno.addAll(pesquisaPorNomeDosSocios(textoDigitado));

		return retorno;
	}

	private List<String> pesquisaPorNomeDosSocios(String textoDigitado) {
		Query pesquisa = sessao
				.createQuery("SELECT DISTINCT socio.nome "
						+ " FROM Empresa AS empresa "
						+ " LEFT JOIN empresa.socios AS socio "
						+ " WHERE lower(unaccent(socio.nome)) LIKE lower(unaccent(:busca))");

		pesquisa.setParameter("busca", "%" + textoDigitado + "%");

		return pesquisa.list();
	}

	private List<String> pesquisaPorCampo(String textoDigitado,
			String campoParaProcurar) {
		Query pesquisa = sessao.createQuery("SELECT DISTINCT empresa."
				+ campoParaProcurar + " FROM Empresa AS empresa "
				+ " WHERE lower(unaccent(empresa." + campoParaProcurar
				+ ")) LIKE lower(unaccent(:busca)) ");

		pesquisa.setParameter("busca", "%" + textoDigitado + "%");

		return pesquisa.list();
	}

	private Query montarPesquisaAvancada(Empresa empresa, String operador) {
		empresa.retiraPontosTracosBarrasCnpjECpf();
		
		StringBuilder sqlPesquisa = new StringBuilder();		
		
		sqlPesquisa.append("SELECT DISTINCT empresa FROM Empresa AS empresa ");

		if (empresa.getSocios().size() > 0) {
			sqlPesquisa.append(" LEFT JOIN empresa.socios AS socio WHERE ");

			for (int i = 0; i < empresa.getSocios().size(); i++) {

				if (empresa.getSocios().get(i).getNome() != null) {
					sqlPesquisa.append("lower(unaccent(socio.nome)) LIKE lower(unaccent(:nome" + i + ")) " + operador );
				}

				if (empresa.getSocios().get(i).getCpf() != null) {
					sqlPesquisa.append("socio.cpf LIKE :cpf" + i + " " + operador );
				}
			}

		} else {
			sqlPesquisa.append(" WHERE ");
		}
		

		if (empresa.getCnpj() != null) {			
			sqlPesquisa.append("empresa.cnpj LIKE :cnpj " + operador );		
		}

		if (empresa.getRazaoSocial() != null) {
			sqlPesquisa.append("lower(unaccent(empresa.razaoSocial)) LIKE lower(unaccent(:razaoSocial)) " + operador );
		}

		if (empresa.getNomeFantasia() != null) {
			sqlPesquisa.append("lower(unaccent(empresa.nomeFantasia)) LIKE lower(unaccent(:nomeFantasia)) " + operador );
		}
		
		if (empresa.getEndereco() != null) {
			if (empresa.getEndereco().getCidade() != null) {
				sqlPesquisa.append("lower(unaccent(empresa.cidade)) LIKE lower(unaccent(:cidade)) " + operador );
			}

			if (empresa.getEndereco().getUf() != null) {
				sqlPesquisa.append("lower(unaccent(empresa.uf)) LIKE lower(unaccent(:uf)) " + operador );
			}

			if (empresa.getEndereco().getLogradouro() != null) {
				sqlPesquisa.append("lower(unaccent(empresa.logradouro)) LIKE lower(unaccent(:logradouro)) " + operador );
			}
		}
				
		Query pesquisa = sessao.createQuery(sqlPesquisa.substring(0, sqlPesquisa.length() - operador.length()));
			
		if (empresa.getNomeFantasia() != null) {
			pesquisa.setParameter("nomeFantasia", "%" + empresa.getNomeFantasia()+ "%");			
		}
		if (empresa.getRazaoSocial() != null) {
			pesquisa.setParameter("razaoSocial", "%" + empresa.getRazaoSocial()	+ "%");
		}
		if (empresa.getCnpj() != null) {	
			pesquisa.setParameter("cnpj", "%" + empresa.getCnpj()+ "%");
		}
		if (empresa.getEndereco() != null) {
			if (empresa.getEndereco().getLogradouro() != null) {
				pesquisa.setParameter("logradouro", "%"	+ empresa.getEndereco().getLogradouro() + "%");
			}
			if (empresa.getEndereco().getCidade() != null) {
				pesquisa.setParameter("cidade", "%" + empresa.getEndereco().getCidade()	+ "%");
			}
			if (empresa.getEndereco().getUf() != null) {
				pesquisa.setParameter("uf", "%" + empresa.getEndereco().getUf() + "%");
			}
		}
		

		for (int i = 0; i < empresa.getSocios().size(); i++) {
			

			if (empresa.getSocios().get(i).getNome() != null) {

				pesquisa.setParameter("nome" + i , "%" + empresa.getSocios().get(i).getNome() + "%");
			}
			if (empresa.getSocios().get(i).getCpf() != null) {

				pesquisa.setParameter("cpf" + i , "%" + empresa.getSocios().get(i).getCpf() + "%");
			}
			
		}
		
		return pesquisa;
	}

	@Override
	public List<Empresa> pesquisaAvancadaEspecifica(Empresa empresa) {
		Query pesquisa = montarPesquisaAvancada(empresa, OperadoresSQL.AND);
		return pesquisa.list();
	}

	@Override
	public List<Empresa> pesquisaAvancadaAproximada(Empresa empresa) {
		Query pesquisa = montarPesquisaAvancada(empresa, OperadoresSQL.OR);
		return pesquisa.list();
	}
}