package br.com.aceleradora.RegistroLivre.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.aceleradora.RegistroLivre.model.Empresa;
import br.com.caelum.vraptor.ioc.Component;

@Component
public class EmpresaDAO implements IEmpresaDAO{
	
	private Session sessao;
	
	public EmpresaDAO(Session sessao){
		this.sessao = sessao;
	}
	
	public List<Empresa> getTodas(){	
		return sessao.createQuery("FROM Empresa").list();		
	}
	
	public Empresa getById(long id){
		try{
			Empresa empresa = (Empresa) sessao.get(Empresa.class, id);
			return empresa;
		}catch(Exception e){
			return null;
		}
	}

	@Override
	public void adiciona(Empresa empresa) {		
		Transaction transacao = sessao.beginTransaction();
		sessao.save(empresa);
		transacao.commit();
	}
}
