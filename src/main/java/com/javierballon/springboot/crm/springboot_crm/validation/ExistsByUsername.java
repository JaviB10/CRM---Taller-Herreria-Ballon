package com.javierballon.springboot.crm.springboot_crm.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Constraint(validatedBy = ExistsByUsernameValidation.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ExistsByUsername {

    String message() default "{El username ya esta en uso}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
