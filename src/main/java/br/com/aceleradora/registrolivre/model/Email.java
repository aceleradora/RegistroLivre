package br.com.aceleradora.registrolivre.model;

public class Email {
	
	private String nome;
	private String remetente;
	private String assunto;
	private String mensagem;
	
	public Email(String nome, String rementente, String assunto, String mensagem) {
		this.nome = nome;
		this.remetente = rementente;
		this.assunto = assunto;
		this.mensagem = mensagem;
	}

	public String getNome() {
		return nome;
	}

	public String getRemetente() {
		return remetente;
	}

	public String getAssunto() {
		return assunto;
	}

	public String getMensagem() {
		return mensagem;
	}

}
