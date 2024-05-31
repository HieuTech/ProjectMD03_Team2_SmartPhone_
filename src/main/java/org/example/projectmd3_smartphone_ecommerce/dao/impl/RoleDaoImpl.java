package org.example.projectmd3_smartphone_ecommerce.dao.impl;

import org.example.projectmd3_smartphone_ecommerce.dao.IRoleDao;
import org.example.projectmd3_smartphone_ecommerce.entity.Roles;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Repository

@Transactional
public class RoleDaoImpl implements IRoleDao {
    @Autowired
    private SessionFactory sessionFactory;


    @Override
    @Transactional
    public List<Roles> getAllRoles() {
        List<Roles> roles = new ArrayList<>();
        try {
            Session session = sessionFactory.openSession();
            Query<Roles> query = session.createQuery("FROM Roles", Roles.class);
            roles = query.getResultList();
            session.close();
        } catch (HibernateException ex) {
            ex.printStackTrace(); // Example: Print stack trace for debugging
        }
        return roles;
    }

    @Override
    public List<Roles> getAll(Integer currentPage, Integer size) {
        return null;
    }

    @Override
    public Roles findById(Integer id) {
        Roles role = new Roles();
        try {
            Session session = sessionFactory.openSession();
            role = session.createQuery("FROM Roles WHERE id=:id", Roles.class).setParameter("id", id).getSingleResult();
            session.close();
        } catch (HibernateException ex) {
            ex.printStackTrace(); // Example: Print stack trace for debugging
        }
        return role;
    }

    @Override
    public boolean addNew(Roles object) {
        return false;
    }

    @Override
    public boolean update(Roles object, Integer id) {
        return false;
    }

    @Override
    public boolean delete(Integer id) {
        return false;
    }
}
