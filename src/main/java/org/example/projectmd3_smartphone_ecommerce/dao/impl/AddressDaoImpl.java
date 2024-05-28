package org.example.projectmd3_smartphone_ecommerce.dao.impl;

import org.example.projectmd3_smartphone_ecommerce.dao.IAddressDao;
import org.example.projectmd3_smartphone_ecommerce.entity.Address;
import org.example.projectmd3_smartphone_ecommerce.entity.ShoppingCarts;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AddressDaoImpl implements IAddressDao {
    @Autowired
    private SessionFactory sessionFactory;
    @Override
    public List<Address> getAll() {
        Session session = sessionFactory.openSession();

        try{
            List<Address> list = session.createQuery("from Address ").list();
            return list;
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            session.close();
        }
        return null;
    }

    @Override
    public Address findById(Integer id) {
        Session session = sessionFactory.openSession();

        try{
            return session.get(Address.class, id);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            session.close();
        }
        return null;
    }

    @Override
    public List<Address> findByUserId(Integer userId) {
        Session session = sessionFactory.openSession();
        try{
            String hql = "SELECT s from Address s where s.users.id = :userId";
            List<Address> address =(List<Address>) session.createQuery(hql).setParameter("userId",userId).list();
            return address;
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            session.close();
        }

        return null;
    }

    @Override
    public boolean addNew(Address address) {
        Session session = sessionFactory.openSession();

        try{
            session.beginTransaction();
            session.save(address);
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
    public boolean update(Address address) {
        Session session = sessionFactory.openSession();
        try{
            session.beginTransaction();
            session.update(address);
            session.getTransaction().commit();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            session.getTransaction().rollback();
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

}

