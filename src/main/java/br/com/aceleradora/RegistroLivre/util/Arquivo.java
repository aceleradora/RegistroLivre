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

	public static String uploadReturnUrl(File file) throws IOException {
		String cloudName = System.getenv("CLOUD_NAME");
		String apiKey = System.getenv("API_KEY");
		String apiSecret = System.getenv("API_SECRET");

		Cloudinary cloudinary = new Cloudinary(Cloudinary.asMap("cloud_name",
				cloudName, "api_key", apiKey, "api_secret", apiSecret));

		SingletonManager manager = new SingletonManager();
		manager.setCloudinary(cloudinary);
		manager.init();

		cloudinary.uploader().upload(file, cloudinary.emptyMap());

		try {
			Map uploadResult = cloudinary.uploader().upload(file,
					Cloudinary.emptyMap());

			String url = (String) uploadResult.get("url");
			return url;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}

	}
}
