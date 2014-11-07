package br.com.aceleradora.RegistroLivre.controller;

import br.com.aceleradora.RegistroLivre.model.Empresa;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Resource;

@Resource
public class EmpresaController {
	
	//private EmpresaDAO dao;

//	public CadastroController(EmpresaDAO dao){
//		this.dao = dao;
//	}
	
	@Get("/cadastro")
	public void cadastro() {
		
	}
	
	@Get("/visualizacao")
	public void visualizacao(){
		
	}
	
//	public void cadastrar(Empresa empresa){
//		EmpresaDAO.adiciona(empresa);
//	}
	
}
