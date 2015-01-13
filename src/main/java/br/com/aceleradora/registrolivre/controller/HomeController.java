package br.com.aceleradora.registrolivre.controller;

import br.com.aceleradora.registrolivre.dao.IEmpresaDAO;
import br.com.aceleradora.registrolivre.model.Empresa;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;

@Resource
public class HomeController {

	private IEmpresaDAO daoEmpresa;
	private Result result;

	public HomeController(IEmpresaDAO daoEmpresa, Result result) {
		this.daoEmpresa = daoEmpresa;
		this.result = result;
	}

	@Get("/")
	public void home() {		
	}
	
	@Get("/insere")
	public void insereEmpresas(int n){
		System.out.println(n);
		
		for(int index = 0; index < n; index++){
			Empresa empresa = new Empresa();
			
			empresa.setCnpj("36.150.502/0001-46");
			empresa.setNomeFantasia("Nome Fantasia Teste");
			empresa.setUrl("http://res.cloudinary.com/dhqchbqit/image/upload/v1418990825/rangel_19_12_2014_10:07:01.pdf");
			
			daoEmpresa.salva(empresa);
		}
		
		result.redirectTo(this).home();
	}
	
	@Get("/sobre")
	public void sobre(){
	}
}