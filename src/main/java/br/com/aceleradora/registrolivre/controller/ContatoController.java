package br.com.aceleradora.registrolivre.controller;


import br.com.aceleradora.registrolivre.model.Email;
import br.com.aceleradora.registrolivre.util.EmissorDeEmail;
import br.com.caelum.vraptor.Get;
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

	public void enviar(Email email){
		EmissorDeEmail emissor = new EmissorDeEmail(); 
		emissor.enviar(email);
		result.include("enviar", "Mensagem enviada com sucesso!");
		result.forwardTo(this).contato();
	}	
}
