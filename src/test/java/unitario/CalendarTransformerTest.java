package unitario;

import static org.junit.Assert.assertTrue;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.junit.Test;

import br.com.aceleradora.registrolivre.util.CalendarTransformador;
import flexjson.JSONSerializer;

public class CalendarTransformerTest {

	@Test
	public void retornaUmJSonComDataFormatadaQuandoUtilizarOCalerdarTransformer() throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Calendar data = Calendar.getInstance();
		data.setTime(sdf.parse("16/12/2014"));
		
		JSONSerializer json = new JSONSerializer();
		
		String result = json
				.transform(new CalendarTransformador("dd/MM/yyyy"), Calendar.class)
				.serialize(data);

		assertTrue(result.contains("16/12/2014"));
	}
}
