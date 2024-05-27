package org.example.projectmd3_smartphone_ecommerce.validator;

import org.example.projectmd3_smartphone_ecommerce.dto.request.AuthenRequest;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, AuthenRequest> {
    @Override
    public void initialize(PasswordMatches constraintAnnotation) {
    }

    @Override
    public boolean isValid(AuthenRequest authenRequest, ConstraintValidatorContext context) {
        if (authenRequest.getPassword() == null || authenRequest.getRepeatPassword() == null) {
            return false;
        }
        boolean isValid = authenRequest.getPassword().equals(authenRequest.getRepeatPassword());
        if (!isValid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Mật khẩu không khớp!")
                    .addPropertyNode("repeatPassword")
                    .addConstraintViolation();
        }
        return isValid;
    }
}
