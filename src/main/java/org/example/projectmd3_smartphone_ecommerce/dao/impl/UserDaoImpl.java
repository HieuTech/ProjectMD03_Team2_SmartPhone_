package org.example.projectmd3_smartphone_ecommerce.dao.impl;

import org.example.projectmd3_smartphone_ecommerce.dao.IUserDao;
import org.example.projectmd3_smartphone_ecommerce.dto.request.UserRequest;
import org.example.projectmd3_smartphone_ecommerce.entity.Users;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDaoImpl  implements IUserDao {
    @Autowired
    private SessionFactory sessionFactory;



    @Override
    public List<Users> getAll(Integer currentPage, Integer size) {
        return null;
    }

    @Override
    public Users findById(Integer id) {
        Session session = sessionFactory.openSession();
        try {
            return session.get(Users.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return null;
    }

    @Override
    public boolean addNew(UserRequest object) {
        return false;
    }

    @Override
    public boolean update(UserRequest object, Integer id) {
        return false;
    }

    @Override
    public boolean delete(Integer id) {
        return false;
    }
}
