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
	if (email.getNome() != null && 
			email.getMensagem() != null &&
			email.getRemetente() != null &&
			email.getRemetente().contains("@") &&
			email.getAssunto() != null)
			{
				EmissorDeEmail emissor = new EmissorDeEmail(); 
					emissor.enviar(email);
					result.include("enviar", "Mensagem enviada com sucesso!");
					result.forwardTo(this).contato();
			} else {
				result.include("erro", "Por favor preencha todos os campos!");
				result.include(email);
				result.redirectTo(this).contato();
		}
	}	

}
