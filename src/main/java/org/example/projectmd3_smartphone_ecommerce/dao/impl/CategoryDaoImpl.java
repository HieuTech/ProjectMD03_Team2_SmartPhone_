package org.example.projectmd3_smartphone_ecommerce.dao.impl;

import org.example.projectmd3_smartphone_ecommerce.dao.ICategoryDao;
import org.example.projectmd3_smartphone_ecommerce.dto.request.CategoryRequest;
import org.example.projectmd3_smartphone_ecommerce.entity.Categories;
import org.example.projectmd3_smartphone_ecommerce.entity.Products;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
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
    public List<Categories> getAll() {
        Session session = sessionFactory.openSession();

        try {
            List<Categories> list = session.createQuery("from Categories").list();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return null;
    }

    @Override
    public Categories findById(Integer categoryId) {
        Session session = sessionFactory.openSession();
        try {
            return session.get(Categories.class, categoryId);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return null;
    }

    @Override
    public boolean addNew(CategoryRequest object) {
        Session session = sessionFactory.openSession();
        Products product = modelMapper.map(object, Products.class);
        try{
            session.beginTransaction();
            session.save(product);
            session.getTransaction().commit();
            return true;
        }catch (Exception e){

            e.printStackTrace();
            //neu that bai phai khoi phuc lai du lieu
            session.getTransaction().rollback();
        }finally {
            session.close();
        }
        return false;
        
    }

    @Override
    public boolean update(CategoryRequest object, Integer id) {
        return false;
    }

    @Override
    public boolean delete(Integer id) {
        return false;
    }
}
