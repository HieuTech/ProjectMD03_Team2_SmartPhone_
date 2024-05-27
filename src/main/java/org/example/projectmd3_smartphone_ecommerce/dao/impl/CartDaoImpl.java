package org.example.projectmd3_smartphone_ecommerce.dao.impl;

import org.example.projectmd3_smartphone_ecommerce.dao.ICartDao;
import org.example.projectmd3_smartphone_ecommerce.dto.request.CartRequest;
import org.example.projectmd3_smartphone_ecommerce.dto.response.CartResponse;
import org.example.projectmd3_smartphone_ecommerce.entity.Products;
import org.example.projectmd3_smartphone_ecommerce.entity.ShoppingCarts;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CartDaoImpl implements ICartDao {
    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private UserDaoImpl userDao;
    @Autowired
    private ProductDaoImpl productDao;

    @Override
    public List<ShoppingCarts> showAllCartByUserId(Integer userId) {
        Session session = sessionFactory.openSession();
        try{
            String hql = "SELECT s from ShoppingCarts s where s.users.id = :userId";
            List<ShoppingCarts> shoppingCarts =(List<ShoppingCarts>) session.createQuery(hql).setParameter("userId",userId).list();
            return shoppingCarts;
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            session.close();
        }
        return null;
    }

    @Override
    public boolean updateCart(CartRequest req) {
        Session session = sessionFactory.openSession();
        try{
            session.beginTransaction();

            session.save(ShoppingCarts.builder().
                    id((req.getId())).users(userDao.findById(req.getId())).products(productDao.findById(req.getProductId()))
                    .orderQuantity(req.getOrderQuantity()).
                    build());
            session.getTransaction().commit();
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            session.close();
        }
        return false;
    }

    @Override
    public ShoppingCarts findByCartId(Integer cartId) {
        Session session = sessionFactory.openSession();

        try{
            return session.get(ShoppingCarts.class, cartId);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            session.close();
        }
        return null;
    }

    @Override
    public boolean addToCart(CartRequest req) {
        Session session = sessionFactory.openSession();
        try{
            session.beginTransaction();


            session.save(ShoppingCarts.builder()
                    .users(userDao.findById(req.getUserId()))
                    .products(productDao.findById(req.getProductId()))
                    .orderQuantity(req.getOrderQuantity())

                    .build());

            session.getTransaction().commit();
            return true;

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            session.close();
        }
        return true;
    }
}