package unitario;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import br.com.aceleradora.registrolivre.controller.ContatoController;
import br.com.aceleradora.registrolivre.model.Email;
import br.com.aceleradora.registrolivre.util.EmissorDeEmail;
import br.com.caelum.vraptor.Result;

@RunWith(MockitoJUnitRunner.class)
public class ContatoControllerTest {
	
	@Mock Result result;
	private String nome;
	private String remetente;
	private String assunto;
	private String mensagem;
    @Mock EmissorDeEmail emissorDeEmail;
	private ContatoController controller;
	Email formDeEmail;
	
	@Before
	public void setUp(){
		nome = "Usuario";
		remetente = "usuario@email.com";
		assunto = "Dúvida";
		mensagem = "Mensagem do e-mail";
		controller = new ContatoController(result);

	}
	
	@Test
	public void quandoUsuarioPreencheTodosOsCamposCorretamenteRetornaTrue() throws Exception {
		formDeEmail = new Email(nome, remetente, assunto, mensagem);
		
		boolean expected = controller.validaCamposFormulario(formDeEmail);
		
		assertThat(expected, is(true));
	}
	
	@Test
	public void quandoUsuarioNaoPreencherNomeRetornaFalso() throws Exception {
	    formDeEmail = new Email(null, remetente, assunto, mensagem);
	    
		boolean expected = controller.validaCamposFormulario(formDeEmail);
		
		assertThat(expected, is(false));
	}
	
	@Test
	public void quandoUsuarioNaoPreencherEmailCorretamenteRetornaFalso() throws Exception {
		formDeEmail = new Email(nome, "emailErrado", assunto, mensagem);
		
		boolean expected = controller.validaCamposFormulario(formDeEmail);
		
		assertThat(expected, is(false));
	}
	
	@Test
	public void quandoUsuarioDeixaDoisCamposEmBrancoRetornaFalso() throws Exception {
		formDeEmail = new Email(nome, remetente, null, null);
		
		boolean expected = controller.validaCamposFormulario(formDeEmail);
		
		assertThat(expected, is(false));
	}
	

}
