package org.example.projectmd3_smartphone_ecommerce.dao.impl;

import org.example.projectmd3_smartphone_ecommerce.dao.IUserDao;
import org.example.projectmd3_smartphone_ecommerce.dto.request.UserRequest;
import org.example.projectmd3_smartphone_ecommerce.entity.Address;
import org.example.projectmd3_smartphone_ecommerce.entity.Roles;
import org.example.projectmd3_smartphone_ecommerce.entity.UserRoles;
import org.example.projectmd3_smartphone_ecommerce.entity.Users;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;



import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


@Repository

@Transactional
public class UserDaoImpl implements IUserDao {

    @Autowired
    private SessionFactory sessionFactory;
    @PersistenceContext
    private EntityManager entityManager;


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


//    @Override
//    public boolean addNewV2(Users object) {
//        try (Session session = sessionFactory.openSession()) {
//            session.beginTransaction();
//            session.save(object);
//
//            UserRoles userRoles = new UserRoles();
//            userRoles.setUsers(object);
//
//            Roles role = (Roles) session.createQuery("FROM Roles WHERE roleName = :roleName", Roles.class)
//                    .setParameter("roleName", "USER")
//                    .uniqueResult();
//            userRoles.setRole(role);
//
//            session.save(userRoles);
//            session.getTransaction().commit();
//            return true;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return false;
//        }
//    }


    @Override
    public List<Users> getUserList(int page, int pageSize, String keyword, String sortBy, String sortOrder) {
        // Calculate the offset for pagination
        int offset = (page - 1) * pageSize;

        // Build the base query
        StringBuilder queryString = new StringBuilder("SELECT u FROM Users u");

        // Add conditions based on keyword
        if (keyword != null && !keyword.isEmpty()) {
            queryString.append(" WHERE u.email LIKE :keyword OR u.userName LIKE :keyword");
        }

        if (sortBy != null && !sortBy.isEmpty()) {
            queryString.append(" ORDER BY ");
            queryString.append(sortBy);

        }
        if (sortOrder != null) {
            queryString.append(" ");
            queryString.append(sortOrder);
        }

        // Create the query object
        javax.persistence.Query query = entityManager.createQuery(queryString.toString());

        // Set parameter for keyword if applicable
        if (keyword != null && !keyword.isEmpty()) {
            query.setParameter("keyword", "%" + keyword + "%");
        }

        // Set pagination limits
        query.setFirstResult(offset);
        query.setMaxResults(pageSize);

        // Execute the query and return the result list
        return query.getResultList();
    }


    @Override
    public Integer getTotalPages(int pageSize, String keyword) {
        StringBuilder queryString = new StringBuilder("SELECT COUNT(u.id) FROM Users u");

        if (keyword != null && !keyword.isEmpty()) {
            queryString.append(" WHERE u.email LIKE :keyword OR u.userName LIKE :keyword");
        }

        Query query = (Query) entityManager.createQuery(queryString.toString());

        if (keyword != null && !keyword.isEmpty()) {
            query.setParameter("keyword", "%" + keyword + "%");
        }

        long totalUsers = (long) query.getSingleResult();
        return (int) Math.ceil((double) totalUsers / pageSize);
    }

    @Override
    public boolean uniquePhoneNumber(String phoneNumber) {
        try (Session session = sessionFactory.openSession()) {
            Query<?> query = session.createQuery("SELECT COUNT(*) FROM Address where phone= :phone");
            query.setParameter("phone", phoneNumber);
            Long count = (Long) query.uniqueResult();
            return count == 0;
        }    }

    @Override
    public boolean update(Users users) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.update(users);
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

    @Override
    public boolean addNewUser(Users user) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            

            session.save(user); // This should cascade and save the address as well if cascading is properly set

            // Set up user roles
            UserRoles userRoles = new UserRoles();
            userRoles.setUsers(user);

            Roles role = (Roles) session.createQuery("FROM Roles WHERE roleName = :roleName", Roles.class)
                    .setParameter("roleName", "USER")
                    .uniqueResult();
            userRoles.setRole(role);

            session.save(userRoles); // Save the user roles

            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }



}
