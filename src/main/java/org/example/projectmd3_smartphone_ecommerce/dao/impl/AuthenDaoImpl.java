package org.example.projectmd3_smartphone_ecommerce.dao.impl;

import org.example.projectmd3_smartphone_ecommerce.dao.IAuthenDao;
import org.example.projectmd3_smartphone_ecommerce.dto.request.AuthenRequest;
import org.example.projectmd3_smartphone_ecommerce.entity.Users;
import org.springframework.stereotype.Repository;

@Repository
public class AuthenDaoImpl implements IAuthenDao {
    @Override
    public boolean register(AuthenRequest request) {
        return false;
    }

    @Override
    public Users login(AuthenRequest request) {
        return null;
    }

    @Override
    public void logout() {

    }
}
