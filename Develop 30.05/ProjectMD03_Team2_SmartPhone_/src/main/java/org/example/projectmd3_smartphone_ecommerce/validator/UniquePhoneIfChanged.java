package org.example.projectmd3_smartphone_ecommerce.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniquePhoneIfChangedValidator.class)
public @interface UniquePhoneIfChanged {
    String message() default "Phone number must be unique!";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
