package br.com.aceleradora.RegistroLivre.controller;

import br.com.aceleradora.RegistroLivre.dao.EmpresaDAO;
import br.com.aceleradora.RegistroLivre.model.Empresa;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;

@Resource
public class EmpresaController {
	
	private EmpresaDAO daoEmpresa;
	
	public EmpresaController(EmpresaDAO dao){
	
		this.daoEmpresa = dao;
	}
	
	@Get("/cadastro")
	public void cadastro() {
		
	}
	
	@Get("/listagem")
	public void listagem(){
		
	}
	
	@Get("/visualizacao")
	public void visualizacao(){
		
	}
	
	public void cadastrar(Empresa empresa, Result result){
		
		daoEmpresa.adiciona(empresa);
		result.redirectTo(this).visualizacao();
	}
	
}
