package br.com.aceleradora.RegistroLivre.controller;

import br.com.aceleradora.RegistroLivre.dao.EmpresaDAO;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;

@Resource
public class HomeController {

	EmpresaDAO daoEmpresa;
	Result result;

	public HomeController(EmpresaDAO daoEmpresa, Result result) {
		this.daoEmpresa = daoEmpresa;
		this.result = result;
	}

	@Get("/")
	public void home() {
	}
}