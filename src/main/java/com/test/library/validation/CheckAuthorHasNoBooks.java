package com.test.library.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = CheckAuthorHasNoBooksValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface CheckAuthorHasNoBooks {

    String message() default "O autor possui livros cadastrados e não pode ser removido";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
