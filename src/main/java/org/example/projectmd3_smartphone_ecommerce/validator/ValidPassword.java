package org.example.projectmd3_smartphone_ecommerce.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PasswordValidator.class)
public @interface ValidPassword {
    String message() default "Mật khẩu phải chứa ít nhất 8 ký tự và bao gồm cả chữ và số";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
