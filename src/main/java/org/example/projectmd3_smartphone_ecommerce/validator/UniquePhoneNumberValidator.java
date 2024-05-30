package org.example.projectmd3_smartphone_ecommerce.validator;
import org.example.projectmd3_smartphone_ecommerce.dao.impl.UserDaoImpl;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniquePhoneNumberValidator implements ConstraintValidator<UniquePhoneNumber, String> {

    private final UserDaoImpl userDao;

    public UniquePhoneNumberValidator(UserDaoImpl userDao) {
        this.userDao = userDao;
    }

    @Override
    public void initialize(UniquePhoneNumber constraintAnnotation) {
    }

    @Override
    public boolean isValid(String phoneNumber, ConstraintValidatorContext context) {
        if (phoneNumber == null || phoneNumber.isEmpty()) {
            return true; // Null or empty values are considered valid
        }
        return userDao.uniquePhoneNumber(phoneNumber); // Custom method to check uniqueness
    }
}
