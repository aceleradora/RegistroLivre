package br.com.aceleradora.registrolivre.util;

import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import br.com.aceleradora.registrolivre.model.Email;

public class EmissorDeEmail {
	private static final String HOST 			= "mx1.hostinger.com.br";
	private static final String EMAIL_HOST 		= "contato@registrolivre.hol.es";
	private static final String PORTA_HOST 		= "2525";
	private static final String DESTINATARIO	= "registrolivreaceleradora@gmail.com";
	
	private Session sessao;
	
	public EmissorDeEmail(){
		Properties propriedades = new Properties();
		
		 propriedades.put("mail.transport.protocol", "smtp"); 
		 propriedades.put("mail.smtp.starttls.enable", "true");
		 propriedades.put("mail.smtp.host", HOST); 
		 propriedades.put("mail.smtp.auth", "true");
		 propriedades.put("mail.smtp.user", EMAIL_HOST); 
		 propriedades.put("mail.debug", "true");
		 propriedades.put("mail.smtp.port", PORTA_HOST); 
		 propriedades.put("mail.smtp.socketFactory.port", PORTA_HOST); 
		 propriedades.put("mail.smtp.socketFactory.fallback", "false");		 

		this.sessao = Session.getDefaultInstance(propriedades,
				new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() 
			{
				return new PasswordAuthentication(EMAIL_HOST, System.getenv("SENHA_EMAIL"));
			}
		});

		this.sessao.setDebug(true);
	}
	
	public void enviar(Email email){
		try {
			Message mensagemMime = new MimeMessage(sessao);
			mensagemMime.setFrom(new InternetAddress(EMAIL_HOST)); 

			Address[] destinatario = InternetAddress.parse(DESTINATARIO);  

			mensagemMime.setRecipients(Message.RecipientType.TO, destinatario);
			mensagemMime.setSubject("[Contato] " + email.getNome() + ": " + email.getAssunto());
			mensagemMime.setContent(email.getMensagem(),"text/html" );
			
			Transport.send(mensagemMime);

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}

}
