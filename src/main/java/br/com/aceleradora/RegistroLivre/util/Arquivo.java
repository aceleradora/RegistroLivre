package br.com.aceleradora.RegistroLivre.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class Arquivo {

	private File arquivo;
	private String urlArquivo;

	public Arquivo(InputStream inputStream, String nome) {
		inputStreamParaFile(inputStream, nome);
	}

	private void inputStreamParaFile(InputStream inputStream, String nome) {
		try {
			File arquivo = new File("/tmp/" + nome.replaceAll("[.\\-/]", ""));
			OutputStream outputStream = new FileOutputStream(arquivo);

			int read = 0;
			byte[] bytes = new byte[1024];

			while ((read = inputStream.read(bytes)) != -1) {
				outputStream.write(bytes, 0, read);
			}
			outputStream.close();
			this.arquivo = arquivo;
		} catch (IOException e) {
			System.out.println("Erro ao criar arquivo: " + e.getMessage());
			e.printStackTrace();
		}
	}

	public File getArquivo() {
		return arquivo;
	}

	public String getUrlArquivo() {
		return this.urlArquivo;
	}

	public void setArquivo(File arquivo) {
		this.arquivo = arquivo;
	}

	public void setUrlArquivo(String urlArquivo) {
		this.urlArquivo = urlArquivo;
	}
}
