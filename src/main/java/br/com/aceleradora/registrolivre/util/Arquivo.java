package br.com.aceleradora.registrolivre.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.Normalizer;
import java.text.SimpleDateFormat;
import java.util.regex.Pattern;

import br.com.aceleradora.registrolivre.model.Empresa;


public class Arquivo {

	private File arquivo;
	private String urlArquivo;
	private String nomeArquivo;

	public void setNomeArquivo(String nomeArquivo) {
		this.nomeArquivo = nomeArquivo;
	}

	public Arquivo(InputStream inputStream, Empresa empresa) {
		String nomeArquivo = formataNomeArquivo(empresa);
		inputStreamParaFile(inputStream, nomeArquivo);
	}

	private String formataNomeArquivo(Empresa empresa) {
		SimpleDateFormat formatacaoDataETempo = new SimpleDateFormat("dd_MM_yyyy_HH:mm:ss");

		String data = formatacaoDataETempo.format(empresa.getDataRegistro().getTime());

		String nomeArquivo = empresa.getNomeFantasia().replace(' ', '_') + "_"
				+ data;
		this.setNomeArquivo(nomeArquivo);
		
		return nomeArquivo;
	}

	private String normalizarNome(String nome) {
		String nomeNormalizado = Normalizer
				.normalize(nome, Normalizer.Form.NFD);
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
