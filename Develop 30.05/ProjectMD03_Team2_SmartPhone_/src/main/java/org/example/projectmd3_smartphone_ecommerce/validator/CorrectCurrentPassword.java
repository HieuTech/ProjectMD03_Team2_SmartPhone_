package org.example.projectmd3_smartphone_ecommerce.validator;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CorrectCurrentPasswordValidator.class)
public @interface CorrectCurrentPassword {
    String message() default "You entered the wrong current password!";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
