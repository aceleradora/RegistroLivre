package br.com.aceleradora.RegistroLivre.controller;

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
//		Entidade entidade = new Entidade();
//		entidade.setNome("Teste");
//		
//		dao.adiciona(entidade);
//		System.out.println("hello world");
	}

}