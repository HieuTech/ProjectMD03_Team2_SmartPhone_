package org.example.projectmd3_smartphone_ecommerce.dao.impl;

import org.example.projectmd3_smartphone_ecommerce.dao.IUserDao;
import org.example.projectmd3_smartphone_ecommerce.dto.request.UserRequest;
import org.example.projectmd3_smartphone_ecommerce.entity.Products;
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
        Session session = sessionFactory.getCurrentSession();
        String hql = "FROM Users";
        Query<Users> query = session.createQuery(hql, Users.class);
        query.setFirstResult((currentPage - 1) * size);
        query.setMaxResults(size);
        return query.list();
    }

    public List<Users> getAllV2() {
        Session session = sessionFactory.openSession();

        try{
            List<Users> list = session.createQuery("from Users ").list();
            return list;
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            session.close();
        }
        return null;

    }
    public Users findByIdV2(Integer id) {
        Session session = sessionFactory.openSession();

        try{
            return session.get(Users.class, id);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            session.close();
        }
        return null;
    }

    @Override
    public Users findById(Integer id) {
        try (Session session = sessionFactory.openSession()) {
            return getAll(1,4).stream().filter(user -> Objects.equals(user.getId(), id)).findFirst().orElse(null);
        }
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
    public boolean addNewV2(Users object) {
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
