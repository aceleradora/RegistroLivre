package br.com.aceleradora.RegistroLivre.controller;

import java.util.List;

import br.com.aceleradora.RegistroLivre.dao.EmpresaDAO;
import br.com.aceleradora.RegistroLivre.model.Empresa;
import br.com.caelum.vraptor.Get;
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
	public List<Empresa> listagem(Result result){
		result.include("totalDeRegistros", daoEmpresa.contaQuantidadeDeRegistros());
		return daoEmpresa.getTodas();
	}
	
	@Get("/visualizacao/{empresa.id}")
	public Empresa visualizacao(Empresa empresa){
		return daoEmpresa.getById(empresa.getId());
	}
	
	public void cadastrar(Empresa empresa, Result result){
		daoEmpresa.adiciona(empresa);
		result.include("mensagem", "true");		
		result.redirectTo(this).visualizacao(empresa);		
	}
	
}
