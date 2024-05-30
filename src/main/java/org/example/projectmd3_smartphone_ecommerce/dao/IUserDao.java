package org.example.projectmd3_smartphone_ecommerce.dao;



import org.example.projectmd3_smartphone_ecommerce.dto.request.UserRequest;
import org.example.projectmd3_smartphone_ecommerce.entity.Address;
import org.example.projectmd3_smartphone_ecommerce.entity.Comment;
import org.example.projectmd3_smartphone_ecommerce.entity.Users;

import java.util.Date;
import java.util.List;

public interface IUserDao extends IGenericDao<Users, UserRequest,Integer> {
    Users getUserByEmail(String email);

    boolean uniqueEmail(String email);

    boolean addNewUser(Users user);


    List<Users> getUserList(int page, int pageSize, String keyword, String sortBy, String sortOrder);

    Integer getTotalPages(int pageSize, String keyword);

    boolean uniquePhoneNumber(String phoneNumber);

    boolean update(Users users);

    Boolean addComment(Comment comment);

    List<Comment> getComment(Integer productID,Integer currentPage, Integer size);

}
