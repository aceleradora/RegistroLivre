package br.com.aceleradora.registrolivre.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.Normalizer;
import java.util.regex.Pattern;

public class Arquivo {

	private File arquivo;
	private String urlArquivo;

	public Arquivo(InputStream inputStream, String nome) {
		inputStreamParaFile(inputStream, nome);
	}
	
	private String normalizarNome(String nome){
		String nomeNormalizado = Normalizer.normalize(nome, Normalizer.Form.NFD);			
	    Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
	    nomeNormalizado = pattern.matcher(nomeNormalizado).replaceAll("");
	    nomeNormalizado = nomeNormalizado.replaceAll("[.\\-/]", "");
	    return nomeNormalizado;
	}

	private void inputStreamParaFile(InputStream inputStream, String nome) {
		try {
			
			String nomeNormalizado = normalizarNome(nome);
		    
			File arquivo = new File("/tmp/" + nomeNormalizado);
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

	public String getNomeArquivo() {
		return arquivo.getName();
	}

	public String getUrlArquivo() {
		return this.urlArquivo;
	}

	public void setUrlArquivo(String urlArquivo) {
		this.urlArquivo = urlArquivo;
	}
}
