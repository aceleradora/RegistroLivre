package br.com.aceleradora.registrolivre.model;

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

//		props.put("mail.smtp.host", "smtp.gmail.com");
//		props.put("mail.smtp.socketFactory.port", "2525");
//		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
//		props.put("mail.smtp.auth", "true");
//		props.put("mail.smtp.port", "465");
		
		
		
		
		 props.put("mail.transport.protocol", "smtp"); // define protocolo de envio como SMTP
		 props.put("mail.smtp.starttls.enable", "true");
		 props.put("mail.smtp.host", "mx1.hostinger.com.br"); // server SMTP do GMAIL
		 props.put("mail.smtp.auth", "true"); // ativa autenticacao
		 props.put("mail.smtp.user", "contato@registrolivre.hol.es"); // usuario ou seja, a conta que esta enviando o email (tem que ser do GMAIL)
		 //props.put("mail.smtp.ehlo", false);
		 props.put("mail.debug", "true");
		 props.put("mail.smtp.port", "2525"); // porta
		 props.put("mail.smtp.socketFactory.port", "2525"); // mesma porta para o socket
		 //props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
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


