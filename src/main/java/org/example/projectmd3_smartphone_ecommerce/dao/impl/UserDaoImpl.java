package org.example.projectmd3_smartphone_ecommerce.dao.impl;

import org.example.projectmd3_smartphone_ecommerce.dao.IUserDao;
import org.example.projectmd3_smartphone_ecommerce.entity.Roles;
import org.example.projectmd3_smartphone_ecommerce.entity.UserRoles;
import org.example.projectmd3_smartphone_ecommerce.entity.Users;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.management.relation.Role;
import java.util.List;
import java.util.Objects;

import java.util.List;

@Repository


@Transactional
public class UserDaoImpl implements IUserDao {

    @Autowired
    private SessionFactory sessionFactory;



    @Override

    public List<Users> getAll(Integer currentPage, Integer size) {
        return null;

    }

    @Override
    public Users findById(Integer id) {

        try (Session session = sessionFactory.openSession()) {
            return getAll().stream().filter(user -> Objects.equals(user.getId(), id)).findFirst().orElse(null);
        }
    }

    @Override
    public boolean addNew(Users object) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.save(object);

            UserRoles userRoles = new UserRoles();
            userRoles.setUsers(object);

            Roles role = (Roles) session.createQuery("FROM Roles WHERE roleName = :roleName", Roles.class)
                    .setParameter("roleName", "USER")
                    .uniqueResult();
            userRoles.setRole(role);

            session.save(userRoles);
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(Users object, Integer id) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            // Update logic here
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(Integer id) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Users users = findById(id);
            if (users != null) {
                users.setIsDeleted(true);
                session.update(users);
            }
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Users getUserByEmail(String email) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM Users WHERE email = :email", Users.class)
                    .setParameter("email", email)
                    .uniqueResult();
        }
    }

    @Override
    public boolean uniqueEmail(String email) {
        try (Session session = sessionFactory.openSession()) {
            Query<?> query = session.createQuery("SELECT COUNT(*) FROM Users WHERE email = :email");
            query.setParameter("email", email);
            Long count = (Long) query.uniqueResult();
            return count == 0;
        }
    }



}
