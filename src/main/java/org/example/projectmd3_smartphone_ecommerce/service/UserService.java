package org.example.projectmd3_smartphone_ecommerce.service;


import org.example.projectmd3_smartphone_ecommerce.dao.impl.UserDaoImpl;
import org.example.projectmd3_smartphone_ecommerce.entity.Comment;
import org.example.projectmd3_smartphone_ecommerce.entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserDaoImpl userDao;

    public List<Comment> getComments(Integer ProductID,Integer currentPage, Integer size) {
        return userDao.getComment(ProductID,currentPage,size);
    }

    public Boolean addComment(Comment comment) {
        return userDao.addComment(comment);
    }


    public Users findByIdV2(Integer id){


        return userDao.findByIdV2(id);
    }

    public Users findByEmail(String email){
        return userDao.getUserByEmail(email);
    }


}
