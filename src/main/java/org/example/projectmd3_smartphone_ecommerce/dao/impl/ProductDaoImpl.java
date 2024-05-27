package org.example.projectmd3_smartphone_ecommerce.dao.impl;

import org.example.projectmd3_smartphone_ecommerce.dao.IProductDAO;
import org.example.projectmd3_smartphone_ecommerce.dto.request.ProductRequest;
import org.example.projectmd3_smartphone_ecommerce.entity.Products;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.List;

@Repository
public class ProductDaoImpl implements IProductDAO {
    @Autowired
    private SessionFactory sessionFactory;


    @Override
    public List<Products> getAll(Integer currentPage, Integer size) {
        Session session = sessionFactory.openSession();
        try {
            // HQL -> Hibernate Query Language
            return session.createQuery("from Products ", Products.class)
                    .setFirstResult(currentPage * size)
                    .setMaxResults(size)
                    .getResultList();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            session.close();
        }
    }

    @Override
    public Products findById(Integer id) {
        Session session = sessionFactory.openSession();
        Products product = (Products) session.get(Products.class, (Serializable) id);
        session.close();
        return product;

    }

    @Autowired
    CategoryDaoImpl categoryDao;

    @Override
    public boolean addNew(ProductRequest product) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.save(product);
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(ProductRequest product, Integer id) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.update(product);
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return false;
    }

    @Override
    public boolean delete(Integer id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(findById(id));
        session.getTransaction().commit();
        session.close();
        return true;
    }



    public List<Products> searchProduct(String productName) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            List<Products> products = session.createQuery("FROM Products WHERE name like :name", Products.class)
                    .setParameter("name", productName)
                    .getResultList();
            session.getTransaction().commit();
            return products;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return null;
    }

    public Long countAllProduct() {
        Session session = sessionFactory.openSession();
        try {
            return (Long) session.createQuery("select count(p.id) from Products p").getSingleResult();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            session.close();
        }
    }

    @Override
    public boolean addNew2(Products p) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.save(p);
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update2(Products p) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.update(p);
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return false;
    }
}
