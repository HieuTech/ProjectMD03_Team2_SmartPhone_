package org.example.projectmd3_smartphone_ecommerce.dao.impl;

import org.example.projectmd3_smartphone_ecommerce.dao.ICategoryDao;
import org.example.projectmd3_smartphone_ecommerce.entity.Categories;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class CategoryDaoImpl implements ICategoryDao {
    @Autowired private SessionFactory sessionFactory;
    @Override
    public List<Categories> showAllCategory() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<Categories> categories = session.createQuery(" from Categories ").list();
        session.getTransaction().commit();
        session.close();
        return categories;
    }

    @Override
    public Boolean addCategory(Categories category) {
        Session session = sessionFactory.openSession();
        try{
            session.beginTransaction();
            session.save(category);
            session.getTransaction().commit();
            return true;
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            session.close();
        }
        return false;
    }

    @Override
    public Boolean updateCategory(Categories category) {
        Session session = sessionFactory.openSession();
        try{
            session.beginTransaction();
            session.update(category);
            session.getTransaction().commit();
            return true;
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            session.close();
        }
        return false;
    }

    @Override
    public void deleteCategory(int id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(getCategoryByID(id));
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public Categories getCategoryByID(int id) {
        Session session = sessionFactory.openSession();
        Categories category = (Categories) session.get(Categories.class, id);
        session.close();
        return category;
    }
}
