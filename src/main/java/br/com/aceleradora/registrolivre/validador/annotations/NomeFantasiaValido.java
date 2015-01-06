package br.com.aceleradora.registrolivre.validador.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.validation.Constraint;
import javax.validation.Payload;

import br.com.aceleradora.registrolivre.validador.validacoes.NomeFantasiaValidador;

@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = NomeFantasiaValidador.class)
public @interface NomeFantasiaValido {
	String message() default "Nome fantasia obrigatório.";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
