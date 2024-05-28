package org.example.projectmd3_smartphone_ecommerce.service;

import org.example.projectmd3_smartphone_ecommerce.dao.IAuthenDao;
import org.example.projectmd3_smartphone_ecommerce.dao.impl.AuthenDaoImpl;
import org.example.projectmd3_smartphone_ecommerce.dao.impl.UserDaoImpl;
import org.example.projectmd3_smartphone_ecommerce.dto.request.AuthenRequest;
import org.example.projectmd3_smartphone_ecommerce.dto.request.FormLogin;
import org.example.projectmd3_smartphone_ecommerce.entity.Users;
import org.hibernate.SessionFactory;
import org.mindrot.jbcrypt.BCrypt;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.NoResultException;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@Service
public class AuthenService implements IAuthenDao {
    @Autowired
    private AuthenDaoImpl authenDao;

    @Override
    public List<Users> getAll() {
        return authenDao.getAll();
    }

    @Override
    public Users findById(Integer id) {
        return authenDao.findById(id);
    }

    @Override
    public boolean register(AuthenRequest authenRequest) {
        return authenDao.register(authenRequest);
    }

    @Override
    public boolean login(FormLogin formLogin) {
        return authenDao.login(formLogin);
    }


    @Override
    public void logout() {
        authenDao.logout();
    }

    @Override
    public void block(Users user) {
        authenDao.block(user);
    }

    @Override
    public List<Users> getUserList(int page, int pageSize, String keyword, String sortBy, String sortOrder) {
        return authenDao.getUserList(page,pageSize,keyword,sortBy,sortOrder);
    }

    @Override
    public Integer getTotalPages(int pageSize, String keyword) {
        return authenDao.getTotalPages(pageSize,keyword);
    }


}
