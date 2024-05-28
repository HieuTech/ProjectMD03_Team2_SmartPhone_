package org.example.projectmd3_smartphone_ecommerce.dao;


import org.example.projectmd3_smartphone_ecommerce.dto.request.UserRequest;
import org.example.projectmd3_smartphone_ecommerce.entity.Users;

import java.util.List;

public interface IUserDao extends IGenericDao<Users, UserRequest,Integer>{
Users getUserByEmail(String email);
boolean uniqueEmail(String email);

    boolean addNewV2(Users object);

    List<Users> getUserList(int page, int pageSize, String keyword, String sortBy, String sortOrder);

    Integer getTotalPages(int pageSize, String keyword);

}
