package com.pawelpluta.day008;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;

@Target({TYPE, ANNOTATION_TYPE, RECORD_COMPONENT})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = MagicLogicValidator.class)
public @interface MagicLogicValidation {
    String message() default "When magic flag is true, magic field value cannot be empty or null";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}