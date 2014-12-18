package br.com.aceleradora.registrolivre.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import flexjson.transformer.AbstractTransformer;

public class DataOrdenadaTransformer extends AbstractTransformer {
	private String dateFormat;
	private String dateFormatNovo;
	private String nomeNovo;

	public DataOrdenadaTransformer(String nomeNovo, String dateFormat,
			String dateFormatNovo) {
		this.dateFormatNovo = dateFormatNovo;
		this.dateFormat = dateFormat;
		this.nomeNovo = nomeNovo;
	}

	public void transform(Object object) {
		if (object != null) {
			getContext().write(
					"\"" + getDataFormatada(dateFormat, object) + "\", \""
							+ getNomeNovo() + "\":\""
							+ getDataFormatada(dateFormatNovo, object) + "\"");
		} else {
			getContext().write(
					"null, \"" + getNomeNovo() + "\":null");
		}
	}

	private String getDataFormatada(String dateFormat, Object object) {
		return getFormatadorData(dateFormat).format(getCalendarData(object));
	}

	private Date getCalendarData(Object object) {
		return ((Calendar) object).getTime();
	}

	private SimpleDateFormat getFormatadorData(String dateFormat) {
		return new SimpleDateFormat(dateFormat);
	}

	public String getNomeNovo() {
		return nomeNovo;
	}
}