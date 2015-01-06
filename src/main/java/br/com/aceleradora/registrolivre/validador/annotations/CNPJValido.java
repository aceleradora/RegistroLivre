package br.com.aceleradora.registrolivre.validador.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.validation.Constraint;
import javax.validation.Payload;

import br.com.aceleradora.registrolivre.validador.validacoes.CNPJValidador;

@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CNPJValidador.class)
@Documented
public @interface CNPJValido {

	String message() default "CNPJ é obrigatório e precisa ser válido.";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
