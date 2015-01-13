package br.com.aceleradora.registrolivre.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import flexjson.transformer.AbstractTransformer;

public class CalendarTransformador extends AbstractTransformer {

	private String dateFormat;
	private ThreadLocal<SimpleDateFormat> formatter = new ThreadLocal<SimpleDateFormat>();

	public CalendarTransformador(String dateFormat) {
		this.dateFormat = dateFormat;
	}

	public void transform(Object object) {
		if (object != null) {
			getContext().writeQuoted(getFormatter().format(((Calendar) object).getTime()));
		} else {
			getContext().write("null");
		}
	}

	private SimpleDateFormat getFormatter() {
		if (formatter.get() == null) {
			formatter.set(new SimpleDateFormat(dateFormat));
		}
		return formatter.get();
	}
}