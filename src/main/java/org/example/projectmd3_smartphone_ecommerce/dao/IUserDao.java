package org.example.projectmd3_smartphone_ecommerce.dao;


import org.example.projectmd3_smartphone_ecommerce.dto.request.AuthenRequest;
import org.example.projectmd3_smartphone_ecommerce.dto.request.FormLogin;
import org.example.projectmd3_smartphone_ecommerce.dto.request.UserRequest;
import org.example.projectmd3_smartphone_ecommerce.entity.Users;

public interface IUserDao extends IGenericDao<Users, UserRequest,Integer>{
Users getUserByEmail(String email);
boolean uniqueEmail(String email);

    boolean addNewV2(Users object);


}
