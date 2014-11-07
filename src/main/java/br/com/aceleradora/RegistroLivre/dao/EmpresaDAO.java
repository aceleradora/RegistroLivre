package br.com.aceleradora.RegistroLivre.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import br.com.aceleradora.RegistroLivre.model.Empresa;
import br.com.caelum.vraptor.ioc.Component;

@Component
public class EmpresaDAO {
	
	private Session sessao;
	
	public EmpresaDAO(Session sessao){
		this.sessao = sessao;
	}
	
	public List<Empresa> getTodas(){	
		return sessao.createQuery("FROM Empresa").list();		
	}
	
	public Empresa getEmpresaById(long id){
		try{
			Empresa empresa = (Empresa) sessao.get(Empresa.class, id);
			return empresa;
		}catch(Exception e){
			return null;
		}
	}
}
