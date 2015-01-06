package unitario.validador;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.mockito.internal.matchers.Not;

public class HibernateValidationUtils {
	private static Validator VALIDADOR;

	static {
		ValidatorFactory fabrica = Validation.buildDefaultValidatorFactory();
		VALIDADOR = fabrica.getValidator();
	}

	public static Set<ConstraintViolation<Object>> validacaoPara(Object objeto,
			String nomeCampo) {
		return VALIDADOR.validateProperty(objeto, nomeCampo);
	}

	public static String noCampo(String nomeCampo) {
		return nomeCampo;
	}

	public static Matcher<Set<ConstraintViolation<Object>>> sucesso() {
		return new PassaValidacao();
	}

	public static Matcher<Set<ConstraintViolation<Object>>> falha() {
		return new Not(new PassaValidacao());
	}

	static class PassaValidacao extends	BaseMatcher<Set<ConstraintViolation<Object>>> {
		@Override
		public boolean matches(Object o) {
			boolean resultado = false;
			if (o instanceof Set) {
				resultado = ((Set) o).isEmpty();
			}
			return resultado;
		}

		@Override
		public void describeTo(Description descricao) {
			descricao.appendText("valid");
		}
	}
}