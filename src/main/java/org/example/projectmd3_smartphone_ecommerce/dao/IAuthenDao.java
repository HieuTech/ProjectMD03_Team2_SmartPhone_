package org.example.projectmd3_smartphone_ecommerce.dao;

import org.example.projectmd3_smartphone_ecommerce.dto.request.AuthenEditRequest;
import org.example.projectmd3_smartphone_ecommerce.dto.request.AuthenRequest;

import org.example.projectmd3_smartphone_ecommerce.dto.request.FormLogin;
import org.example.projectmd3_smartphone_ecommerce.entity.Roles;
import org.example.projectmd3_smartphone_ecommerce.entity.Users;

import java.util.List;


public interface IAuthenDao {
    List<Users> getAll();
    Users findById(Integer id);
    public boolean register(AuthenRequest request);
    boolean update(AuthenEditRequest request);

    public boolean login(FormLogin formLogin);

    public void logout();
    public void block(Users user);

    List<Users> getUserList(int page, int pageSize,String keyword,String sortBy,String sortOrder);
    Integer getTotalPages(int pageSize, String keyword);
    boolean addRole(Users users, Roles role);
    boolean deleteRole(Integer userId, Integer roleId);
}
