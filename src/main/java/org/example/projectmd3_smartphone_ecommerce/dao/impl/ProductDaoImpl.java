package org.example.projectmd3_smartphone_ecommerce.dao.impl;

import org.example.projectmd3_smartphone_ecommerce.dao.IProductDao;
import org.example.projectmd3_smartphone_ecommerce.entity.Products;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Repository
public class ProductDaoImpl implements IProductDao {
    @Autowired
    private SessionFactory sessionFactory;
    @Autowired
    private HttpServletRequest httpServletRequest;

    @Override
    public List<Products> selectAllProducts(int currentPage, int size) {
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
    public Products selectProductById(int id) {
        Session session = sessionFactory.openSession();
        Products product = (Products) session.get(Products.class, id);
        session.close();
        return product;
    }

    @Autowired
    CategoryDaoImpl categoryDao;

    @Override
    public Boolean insertProduct(Products product) {
        if (categoryDao.showAllCategory().size() > 0) {
            try (Session session = sessionFactory.openSession()) {
                session.beginTransaction();
                session.save(product);
                session.getTransaction().commit();
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.err.println("Dont have any category to add. Must add category fist");
        }
        return false;
    }

    @Override
    public Boolean updateProduct(Products product) {
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
    public void deleteProduct(int id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(selectProductById(id));
        session.getTransaction().commit();
        session.close();
    }

    @Override
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

    @Override
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
}
