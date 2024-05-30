package org.example.projectmd3_smartphone_ecommerce.validator;

import org.example.projectmd3_smartphone_ecommerce.dto.request.AuthenEditRequest;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NewPasswordMatchesValidator  implements ConstraintValidator<NewPasswordMatches, AuthenEditRequest> {
    @Override
    public void initialize(NewPasswordMatches constraintAnnotation) {
    }

    @Override
    public boolean isValid(AuthenEditRequest request, ConstraintValidatorContext context) {
        if (request.getPassword() == null || request.getRepeatPassword() == null) {
            return false;
        }
        boolean isValid = request.getPassword().equals(request.getRepeatPassword());
        if (!isValid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("2 passwords don't match!")
                    .addPropertyNode("repeatPassword")
                    .addConstraintViolation();
        }
        return isValid;
    }
}
