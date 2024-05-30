package org.example.projectmd3_smartphone_ecommerce.validator;

import org.example.projectmd3_smartphone_ecommerce.dao.impl.UserDaoImpl;
import org.example.projectmd3_smartphone_ecommerce.dto.request.AuthenEditRequest;
import org.example.projectmd3_smartphone_ecommerce.dto.response.AuthenResponse;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpSession;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CorrectCurrentPasswordValidator implements ConstraintValidator<CorrectCurrentPassword, AuthenEditRequest> {
    @Autowired
    private UserDaoImpl userDao;

    @Autowired
    private HttpSession session;

    @Override
    public void initialize(CorrectCurrentPassword constraintAnnotation) {
    }

    @Override
    public boolean isValid(AuthenEditRequest authenRequest, ConstraintValidatorContext context) {
        AuthenResponse response = (AuthenResponse) session.getAttribute("userLogin");

        String currentPasswordFromDB = userDao.findByIdV2(response.getUserId()).getPassword();

        boolean isCurrentPasswordValid = BCrypt.checkpw(authenRequest.getOldPassword(), currentPasswordFromDB);

        if (!isCurrentPasswordValid) {
            context.disableDefaultConstraintViolation(); // Disable the default error message
            context.buildConstraintViolationWithTemplate("You entered the wrong current password!")
                    .addPropertyNode("oldPassword") // Specify the field associated with the error
                    .addConstraintViolation(); // Add the custom error message
        }

        return isCurrentPasswordValid;
    }
}
