package org.example.projectmd3_smartphone_ecommerce.validator;

import org.example.projectmd3_smartphone_ecommerce.dao.impl.UserDaoImpl;
import org.example.projectmd3_smartphone_ecommerce.dto.response.AuthenResponse;
import org.example.projectmd3_smartphone_ecommerce.entity.Users;
import org.example.projectmd3_smartphone_ecommerce.service.UserService;

import javax.servlet.http.HttpSession;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class UniquePhoneIfChangedValidator implements ConstraintValidator<UniquePhoneIfChanged, String> {

    @Autowired
    private UserDaoImpl userDao;
    @Autowired
    private HttpSession session;

    @Override
    public void initialize(UniquePhoneIfChanged constraintAnnotation) {
    }

    @Override
    public boolean isValid(String phone, ConstraintValidatorContext context) {
        AuthenResponse response = (AuthenResponse) session.getAttribute("userLogin");
        Users userLogin=userDao.findByIdV2(response.getUserId());
        if (phone == null || phone.isEmpty()||userLogin.getPhone().equals(phone)) {
            return true;
        }
        return userDao.uniquePhoneNumber(phone);
    }
}
