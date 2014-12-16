package br.com.aceleradora.registrolivre.controller;

import br.com.aceleradora.registrolivre.dao.EmpresaDAO;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;

@Resource
public class MensagemController {

	Result result;

	public MensagemController(Result result) {
		this.result = result;
	}

	@Get("/mensagem")
	public void mensagem() {
	}
}
