package org.example.projectmd3_smartphone_ecommerce.validator;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class PhoneNumberValidator implements ConstraintValidator<ValidPhoneNumber, String> {

    private static final Pattern PHONE_NUMBER_PATTERN = Pattern.compile("^0[0-9]{9,10}$");

    @Override
    public void initialize(ValidPhoneNumber constraintAnnotation) {
    }

    @Override
    public boolean isValid(String phoneNumber, ConstraintValidatorContext context) {
        if (phoneNumber == null) {
            return true;
        }
        return PHONE_NUMBER_PATTERN.matcher(phoneNumber).matches();
    }
}
