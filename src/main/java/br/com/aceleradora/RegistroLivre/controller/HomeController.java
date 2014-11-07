package br.com.aceleradora.RegistroLivre.controller;

import br.com.aceleradora.RegistroLivre.model.Empresa;
import br.com.aceleradora.RegistroLivre.model.Entidade;
import br.com.aceleradora.RegistroLivre.model.EntidadeDAO;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Resource;

@Resource
public class HomeController {
	
	private EntidadeDAO dao;
	
	public HomeController(EntidadeDAO dao){
		this.dao = dao;
	}
	
	@Get("/")
	public void home() {
		Empresa empresa = new Empresa();
		empresa.setCnpj("654654"); 
		empresa.setNomeFantasia("NomeFantasia");
		
		dao.adiciona(empresa);
		//System.out.println("hello world");
	}

}