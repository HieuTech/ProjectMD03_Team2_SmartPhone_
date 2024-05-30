package org.example.projectmd3_smartphone_ecommerce.dao.impl;

import org.example.projectmd3_smartphone_ecommerce.dao.IOrderDao;
import org.example.projectmd3_smartphone_ecommerce.dto.request.OrdersRequest;
import org.example.projectmd3_smartphone_ecommerce.entity.Orders;
import org.example.projectmd3_smartphone_ecommerce.entity.Products;
import org.example.projectmd3_smartphone_ecommerce.entity.ShoppingCarts;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class OrderDaoImpl implements IOrderDao {
    @Autowired
    private SessionFactory sessionFactory;
    public List<Orders> sortORDER(String sorf, Integer currentPage, Integer size) {
            Session session = sessionFactory.openSession();
            try {
                switch (sorf) {
                    case "totalPrice":
                        session.beginTransaction();
                        return session.createQuery("from Orders ORDER BY totalPrice", Orders.class).setFirstResult(currentPage * size)
                                .setMaxResults(size)
                                .getResultList();

                    case "createdAt":
                        session.beginTransaction();
                        return session.createQuery("from Orders ORDER BY createdAt", Orders.class).setFirstResult(currentPage * size)
                                .setMaxResults(size)
                                .getResultList();
                    default:
                        session.beginTransaction();
                        return session.createQuery("from Orders ORDER BY id", Orders.class).setFirstResult(currentPage * size)
                                .setMaxResults(size)
                                .getResultList();
                }
            } catch (
                    Exception e
            ) {
                e.printStackTrace();
            }
            return null;
        };


    @Override
    public List<Orders> getAllV2() {
        Session session = sessionFactory.openSession();

        try{
            List<Orders> list = session.createQuery("from Orders ").list();
            return list;
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            session.close();
        }
        return null;
    }

    @Override
    public List<Orders> getAll(Integer currentPage, Integer size) {
        return null;
    }

    @Override
    public Orders findById(Integer id) {
        Session session = sessionFactory.openSession();

        try{
            return session.get(Orders.class, id);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            session.close();
        }
        return null;
    }

    @Override
    public List<Orders> findByUserId(Integer userId) {
        Session session = sessionFactory.openSession();
        try{
            String hql = "SELECT o from Orders o where o.users.id = :userId";
            List<Orders> ordersList =(List<Orders>) session.createQuery(hql).setParameter("userId", userId).list();
            return ordersList;
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            session.close();
        }
        return null;
    }

    @Override
    public boolean addNew(Orders orders) {
        Session session = sessionFactory.openSession();

        try{
            session.beginTransaction();
            session.save(orders);
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
    public boolean update(Orders orders) {
        Session session = sessionFactory.openSession();
        try{
            session.beginTransaction();
            session.update(orders);
            session.getTransaction().commit();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        return false;
    }

    @Override
    public boolean addNew(OrdersRequest object) {
        return false;
    }

    @Override
    public boolean update(OrdersRequest object, Integer id) {
        return false;
    }

    @Override
    public boolean delete(Integer id) {
        return false;
    }
}
