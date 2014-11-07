package br.com.aceleradora.RegistroLivre.controller;

import br.com.aceleradora.RegistroLivre.dao.EmpresaDAO;
import br.com.aceleradora.RegistroLivre.model.Empresa;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Resource;

@Resource
public class HomeController {

	private EmpresaDAO dao;

	public HomeController(EmpresaDAO dao) {
		this.dao = dao;
	}

	@Get("/")
	public void home() {
		// System.out.println(dao.getEmpresaById(1).getCnpj());
		// Entidade entidade = new Entidade();
		// entidade.setNome("Teste");
		//
		// dao.adiciona(entidade);
		// System.out.println("hello world");
	}
	
	@Get("/listagem")
	public void listagem(){
		
	}

}