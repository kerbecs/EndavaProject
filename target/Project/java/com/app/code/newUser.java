package com.app.code;


import javax.validation.Constraint;
import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@Constraint(validatedBy = newUserValidator.class)
@Target({ElementType.FIELD,ElementType.METHOD})
@
public @interface newUser {
}
