package br.com.aceleradora.RegistroLivre.controller;

import br.com.aceleradora.RegistroLivre.dao.EmpresaDAO;
import br.com.aceleradora.RegistroLivre.model.Empresa;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;

@Resource
public class EmpresaController {
	
	private EmpresaDAO daoEmpresa;
	private Result result;
	
	public EmpresaController(EmpresaDAO dao, Result result){
		
		this.result = result;
		this.daoEmpresa = dao;
	}
	
	@Get("/cadastro")
	public void cadastro() {
		
	}
	
	@Get("/visualizacao")
	public void visualizacao(){
		
	}
	
	public void cadastrar(Empresa empresa){
		daoEmpresa.adiciona(empresa);
	}
	
}
