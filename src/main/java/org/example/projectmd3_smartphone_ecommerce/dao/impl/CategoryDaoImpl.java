package org.example.projectmd3_smartphone_ecommerce.dao.impl;

import org.example.projectmd3_smartphone_ecommerce.dao.ICategoryDao;
import org.example.projectmd3_smartphone_ecommerce.entity.Categories;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.example.projectmd3_smartphone_ecommerce.dto.request.CategoryRequest;
import org.example.projectmd3_smartphone_ecommerce.entity.Products;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CategoryDaoImpl implements ICategoryDao {
    @Autowired
    private SessionFactory sessionFactory;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<Categories> getAll(Integer currentPage, Integer size) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<Categories> categories = session.createQuery(" from Categories ")
                .setFirstResult(currentPage * size)
                .setMaxResults(size)
                .getResultList();
        session.getTransaction().commit();
        session.close();
        return categories;
    }

    @Override
    public boolean addNew(CategoryRequest category) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.save(category);
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;

    }

    public boolean addNew2(Categories category) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.save(category);
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


    @Override
    public boolean update(CategoryRequest category, Integer id) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.update(category);
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return false;
    }

    public boolean update2(Categories category, Integer id) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.update(category);
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

    @Override
    public Categories findById(Integer id) {
        Session session = sessionFactory.openSession();
        Categories category = (Categories) session.get(Categories.class, id);
        session.close();
        return category;
    }

    public Long countAllCategories() {
        Session session = sessionFactory.openSession();
        try {
            return (Long) session.createQuery("select count(c.id) from Categories c").getSingleResult();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            session.close();
        }
    }
}
