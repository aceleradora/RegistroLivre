package br.com.aceleradora.RegistroLivre.controller;

import java.util.List;

import br.com.aceleradora.RegistroLivre.dao.EmpresaDAO;
import br.com.aceleradora.RegistroLivre.dao.SocioDAO;
import br.com.aceleradora.RegistroLivre.model.Empresa;
import br.com.aceleradora.RegistroLivre.model.Validador;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;

@Resource
public class EmpresaController {

	private EmpresaDAO daoEmpresa;
	private Result result;

	public EmpresaController(EmpresaDAO dao, Result result) {
		this.daoEmpresa = dao;
		this.result = result;
	}

	@Get("/cadastro")
	public void cadastro() {

	}

	@Get("/listagem")
	public List<Empresa> listagem() {
		result.include("totalDeRegistros",
				daoEmpresa.contaQuantidadeDeRegistros());
		return daoEmpresa.getTodas();
	}

	@Get("/visualizacao/{empresa.id}")
	public Empresa visualizacao(Empresa empresa) {
		return daoEmpresa.getById(empresa.getId());
	}

	public void cadastrar(Empresa empresa) {
		if (!Validador.verificaCnpj(empresa.getCnpj())) {
			result.include("mensagem", "CNPJ");
			result.redirectTo(this).cadastro();
		} else if (!Validador.verificaCpfListaSocio(empresa.getSocios())) {
			result.include("mensagem", "CPF");
			result.redirectTo(this).cadastro();
		} else {
			daoEmpresa.adiciona(empresa);
			result.include("mensagem", "true");
			result.redirectTo(this).visualizacao(empresa);
		}
	}
		
}
