package org.example.projectmd3_smartphone_ecommerce.dao;

import org.example.projectmd3_smartphone_ecommerce.dto.request.AuthenRequest;
import org.example.projectmd3_smartphone_ecommerce.entity.Users;

public interface IAuthenDao {
    public boolean register(AuthenRequest request);
    public Users login(AuthenRequest request);
    public void logout();
}
