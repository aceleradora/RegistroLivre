package br.com.aceleradora.registrolivre.controller;

import br.com.aceleradora.registrolivre.util.Contato;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;

@Resource
public class ContatoController {

	Result result;

	public ContatoController(Result result) {
		this.result = result;
	}
	
	@Get("/contato")
	public void contato() {
		
	}

	public void enviar(Contato contato){
		contato.enviarEmailContato();
		result.include("contato", "Mensagem enviada com sucesso!");
		result.redirectTo(this).contato();
	}
	
	
}
