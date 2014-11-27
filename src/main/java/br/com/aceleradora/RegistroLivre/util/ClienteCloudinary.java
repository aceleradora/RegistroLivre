package br.com.aceleradora.RegistroLivre.util;

import java.util.Map;

import com.cloudinary.Cloudinary;
import com.cloudinary.SingletonManager;

public class ClienteCloudinary {

	private static final String CLOUD_NAME = System.getenv("CLOUD_NAME");
	private static final String API_KEY = System.getenv("API_KEY");
	private static final String API_SECRET = System.getenv("API_SECRET");

	private Cloudinary cloudinary;
	private Map uploadResult;
	private Arquivo arquivo;

	public ClienteCloudinary(Arquivo arquivo) {
		cloudinary = new Cloudinary(Cloudinary.asMap("cloud_name", CLOUD_NAME,
				"api_key", API_KEY, "api_secret", API_SECRET));
		this.arquivo = arquivo;
	}

	public boolean upload() {
		try {
			SingletonManager manager = new SingletonManager();
			manager.setCloudinary(cloudinary);
			manager.init();
			uploadResult = cloudinary.uploader().upload(arquivo,
					Cloudinary.emptyMap());

			cloudinary.url().generate(arquivo.getArquivo().getName());

			arquivo.setUrlArquivo((String) uploadResult.get("url"));
			return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

	public boolean atualiza(String urlAntiga) {
		String idArquivo = arquivo.getIdArquivo(urlAntiga);

		excluir(idArquivo);

		return upload();
	}

	private void excluir(String idArquivo) {
		SingletonManager manager = new SingletonManager();
		manager.setCloudinary(cloudinary);
		manager.init();
		try {

			cloudinary.api().deleteResourcesByPrefix(
					arquivo.getIdArquivo(idArquivo), Cloudinary.emptyMap());

		} catch (Exception e) {
			System.out.println("Erro ao deletar arquivo no cloudinary: "
					+ e.getMessage());
			e.printStackTrace();
		}
	}

	public Arquivo getArquivo() {
		return arquivo;
	}

}
