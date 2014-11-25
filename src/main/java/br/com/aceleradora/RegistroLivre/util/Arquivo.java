package br.com.aceleradora.RegistroLivre.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;

import com.cloudinary.Cloudinary;
import com.cloudinary.SingletonManager;

public class Arquivo {
	
	private static String cloudName = System.getenv("CLOUD_NAME");
	private static String apiKey = System.getenv("API_KEY");
	private static String apiSecret = System.getenv("API_SECRET");
	
	private static Cloudinary cloudinary = new Cloudinary(Cloudinary.asMap("cloud_name",
			cloudName, "api_key", apiKey, "api_secret", apiSecret));
	
	public static File inputStreamParaFile(InputStream inputStream, String nome) {
		try {
			File arquivo = new File("/tmp/" + nome.replaceAll("[.\\-/]", "")
					+ ".pdf");
			OutputStream outputStream = new FileOutputStream(arquivo);

			int read = 0;
			byte[] bytes = new byte[1024];

			while ((read = inputStream.read(bytes)) != -1) {
				outputStream.write(bytes, 0, read);
			}
			return arquivo;
		} catch (IOException e) {
			return null;
		}
	}

	public static String uploadReturnUrl(File file){
		SingletonManager manager = new SingletonManager();
		manager.setCloudinary(cloudinary);
		manager.init();

		try {
			cloudinary.uploader().upload(file, cloudinary.emptyMap());
			
			Map uploadResult = cloudinary.uploader().upload(file,
					Cloudinary.emptyMap());

			String url = (String) uploadResult.get("url");
			return url;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}

	}
	
	public static String atualiza(String urlAntiga, File novoArquivo){
		String idArquivo = getIdArquivo(urlAntiga);
		String urlArquivoNovo;
		
		excluir(idArquivo);
		urlArquivoNovo = Arquivo.uploadReturnUrl(novoArquivo);
		
		return urlArquivoNovo;
	}
	
	private static String getIdArquivo(String url){
		String [] urlCortada = url.split("/");
		return urlCortada[urlCortada.length - 1].replace(".pdf", "");
	}
	
	private static void excluir(String idArquivo){
		SingletonManager manager = new SingletonManager();
		manager.setCloudinary(cloudinary);
		manager.init();
		try {
			
			cloudinary.api().deleteResourcesByPrefix(getIdArquivo(idArquivo), Cloudinary.emptyMap());			
			
		} catch (Exception e) {
			System.out.println("Erro ao deletar arquivo no cloudinary: " + e.getMessage());
			e.printStackTrace();
		}
	}	
}
