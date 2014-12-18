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

public class Contato {
	
	private String nome;
	private String email;
	private String assunto;
	private String mensagem;
	
	public Contato(String nome, String email, String assunto, String mensagem) {
		this.nome = nome;
		this.email = email;
		this.assunto = assunto;
		this.mensagem = mensagem;
	}

	public String getNome() {
		return nome;
	}

	public String getEmail() {
		return email;
	}

	public String getAssunto() {
		return assunto;
	}

	public String getMensagem() {
		return mensagem;
	}
	
	public void enviarEmailContato(){
		
		Properties props = new Properties();
			
		 props.put("mail.transport.protocol", "smtp"); 
		 props.put("mail.smtp.starttls.enable", "true");
		 props.put("mail.smtp.host", "mx1.hostinger.com.br"); 
		 props.put("mail.smtp.auth", "true");
		 props.put("mail.smtp.user", "contato@registrolivre.hol.es"); 
		 props.put("mail.debug", "true");
		 props.put("mail.smtp.port", "2525"); 
		 props.put("mail.smtp.socketFactory.port", "2525"); 
		 props.put("mail.smtp.socketFactory.fallback", "false");		 

		Session session = Session.getDefaultInstance(props,
				new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() 
			{
				return new PasswordAuthentication("contato@registrolivre.hol.es", System.getenv("SENHA_EMAIL"));
			}
		});

		session.setDebug(true);

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("contato@registrolivre.hol.es")); 

			Address[] toUser = InternetAddress 
					.parse("registrolivreaceleradora@gmail.com");  

			message.setRecipients(Message.RecipientType.TO, toUser);
			message.setSubject("[Contato] " + nome + ": " + assunto);
			message.setText("Enviado de " + email + ":\n\n" + mensagem);
			Transport.send(message);

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
}


