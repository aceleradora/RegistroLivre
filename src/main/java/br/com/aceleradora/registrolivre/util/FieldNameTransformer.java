package br.com.aceleradora.registrolivre.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import flexjson.transformer.AbstractTransformer;

public class FieldNameTransformer extends AbstractTransformer {
	private String dateFormat;
	private String dateFormat2;
	private String transformedFieldName;

	public FieldNameTransformer(String transformedFieldName, String dateFormat,
			String dateFormat2) {
		this.dateFormat2 = dateFormat2;
		this.dateFormat = dateFormat;
		this.transformedFieldName = transformedFieldName;
	}

	public void transform(Object object) {
		if (object != null) {
			getContext().write(
					"\"" + getDataFormatada(dateFormat, object) + "\", \""
							+ getTransformedFieldName() + "\":\""
							+ getDataFormatada(dateFormat2, object) + "\"");
		} else {
			getContext().write("null");
		}
	}

	private String getDataFormatada(String dateFormat, Object object) {
		return getFormatadorData(dateFormat).format(((Calendar) object).getTime());
	}

	private SimpleDateFormat getFormatadorData(String dateFormat) {
		return new SimpleDateFormat(dateFormat);
	}

	public String getTransformedFieldName() {
		return transformedFieldName;
	}
}