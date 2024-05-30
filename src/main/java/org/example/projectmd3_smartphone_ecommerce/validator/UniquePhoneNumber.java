package org.example.projectmd3_smartphone_ecommerce.validator;
import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniquePhoneNumberValidator.class)
public @interface UniquePhoneNumber {

    String message() default "Số điện thoại này đã được sử dụng.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
