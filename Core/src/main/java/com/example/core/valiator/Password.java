package com.example.core.valiator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PasswordConstraintsValidator.class)
public @interface Password {
    String message() default "Invalid password";
    Class[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
