package br.com.aceleradora.registrolivre.util;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class DownloadMultiploArquivo {
	
	private List<String> urls;
	private List<String> caminhoArquivosBaixados;
	private FileOutputStream arquivoGerado;
	private ZipOutputStream zipOutputStream;
	private final String CAMINHO_ARQUIVO_FINAL = "empresas.zip";
	
	public DownloadMultiploArquivo(List<String> urls){
		this.urls = urls;
		
		caminhoArquivosBaixados = new ArrayList<String>();
	}
	
	public String geraZips() throws IOException{
		arquivoGerado = new FileOutputStream(CAMINHO_ARQUIVO_FINAL);
		zipOutputStream = new ZipOutputStream(arquivoGerado);
		
		for(String url : this.urls){
			download(url);
		}
		
		for(String arquivoBaixado : caminhoArquivosBaixados){
			adicionaNoZip(arquivoBaixado, zipOutputStream);
		}
		
		for(String caminhoArquivoBaixado : this.caminhoArquivosBaixados){
			delete(caminhoArquivoBaixado);
		}
		
		zipOutputStream.close();
		arquivoGerado.close();
		
		return CAMINHO_ARQUIVO_FINAL;
	}
	
	private void download(String url) throws IOException {
		String nomeArquivo = url.substring(61);
		URL link = new URL(url);

		InputStream inputStream = new BufferedInputStream(link.openStream());
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int n = 0;

		while (-1 != (n = inputStream.read(buffer))) {
			outputStream.write(buffer, 0, n);
		}

		outputStream.close();
		inputStream.close();
		byte[] arquivoBaixado = outputStream.toByteArray();

		FileOutputStream fileOutputStream = new FileOutputStream(nomeArquivo);
		fileOutputStream.write(arquivoBaixado);
		fileOutputStream.close();

		caminhoArquivosBaixados.add(nomeArquivo);
	}

	private void delete(String fileName) {
		File file = new File(fileName);
		file.delete();
	}
	
	private void adicionaNoZip(String nomeArquivo, ZipOutputStream zipOutputStream) throws IOException{
		File arquivo = new File(nomeArquivo);
		FileInputStream fileInputStream = new FileInputStream(arquivo);
		ZipEntry zipEntry = new ZipEntry(nomeArquivo);
		zipOutputStream.putNextEntry(zipEntry);

		byte[] bytes = new byte[1024];
		int length;
		while ((length = fileInputStream.read(bytes)) >= 0) {
			zipOutputStream.write(bytes, 0, length);
		}

		zipOutputStream.closeEntry();
		fileInputStream.close();
	}
}