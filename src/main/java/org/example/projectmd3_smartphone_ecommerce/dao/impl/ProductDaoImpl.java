package org.example.projectmd3_smartphone_ecommerce.dao.impl;

import org.example.projectmd3_smartphone_ecommerce.dao.IProductDao;
import org.example.projectmd3_smartphone_ecommerce.dto.request.ProductRequest;
import org.example.projectmd3_smartphone_ecommerce.entity.Categories;
import org.example.projectmd3_smartphone_ecommerce.entity.Products;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductDaoImpl implements IProductDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private CategoryDaoImpl categoryDao;

@Autowired
private ModelMapper modelMapper;
    @Override
    public List<Products> getAll() {
        Session session = sessionFactory.openSession();

        try{
            List<Products> list = session.createQuery("from Products").list();
            return list;
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            session.close();
        }
        return null;

    }

    @Override
    public Products findById(Integer id) {
        Session session = sessionFactory.openSession();

        try{
            return session.get(Products.class, id);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            session.close();
        }
        return null;
    }

    @Override
    public boolean addNew(ProductRequest object) {
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
    public boolean update(ProductRequest object, Integer id) {
        Session session = sessionFactory.openSession();
        Products product = modelMapper.map(object, Products.class);
        try{
            session.beginTransaction();
            session.update(product);
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
    public boolean delete(Integer id) {
        Session session = sessionFactory.openSession();
        try{
            session.beginTransaction();
            session.delete(this.findById(id));
            session.getTransaction().commit();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            session.getTransaction().rollback();
        }finally {
            session.close();
        }
        return false;
    }

    @Override
    public List<Products> findProductByName(String productName) {
        Session session = sessionFactory.openSession();
        try {


            if(productName == null || productName.isEmpty()){
                productName = "%";
            }else{

                productName = "%" + productName + "%";
                List<Products> productsList = session.createQuery("from Products where name like : proName")
                        .setParameter("proName",productName)
                        .list();
                return productsList;
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            session.close();
        }
        return null;
    }
}
