package br.com.aceleradora.RegistroLivre.controller;

import java.util.ArrayList;
import java.util.List;

import br.com.aceleradora.RegistroLivre.dao.EmpresaDAO;
import br.com.aceleradora.RegistroLivre.model.Empresa;
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
		System.out.println(daoEmpresa.pesquisaPorCnpj("61.304.622/0001-06"));
	}

	public List<Empresa> buscaPorCnpj(String cnpj) {
		List<Empresa> listaDeEmpresas = new ArrayList<>();

		for (Empresa empresa : daoEmpresa.pesquisaPorCnpj(cnpj)) {
			if (empresa.getCnpj().equals(cnpj)) {
				listaDeEmpresas.add(empresa);
			}
		}

		return listaDeEmpresas;
	}

	public List<Empresa> buscaPorNomeFantasia(String nomeFantasiaRequerido) {
		
		List<Empresa> listaDeEmpresas = new ArrayList<Empresa>();
		
		for (Empresa empresa : daoEmpresa.pesquisaPorNomeFantasia(nomeFantasiaRequerido)) {
			if(empresa.getNomeFantasia().equals(nomeFantasiaRequerido)){
				listaDeEmpresas.add(empresa);
			}
		}
		
		return listaDeEmpresas;
	}
}