package org.example.projectmd3_smartphone_ecommerce.validator;
import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PhoneNumberValidator.class)

public @interface ValidPhoneNumber {
    String message() default "Invalid phone number!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
