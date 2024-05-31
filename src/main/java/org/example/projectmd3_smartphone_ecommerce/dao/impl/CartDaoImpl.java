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

import javax.persistence.Query;
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
                    .users(userDao.findByIdV2(req.getUserId()))
                    .products(productDao.findByIdV2(req.getProductId()))
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

    @Override
    public boolean deleteCart(Integer cartId) {
        Session session = sessionFactory.openSession();
        try{
            session.beginTransaction();
            session.delete(this.findByCartId(cartId));
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
    public boolean deleteCartByUserId(Integer userId) {
        Session session = sessionFactory.openSession();
        try{
            session.beginTransaction();

            // Sử dụng HQL để xóa giỏ hàng dựa trên userId
            String hql = "DELETE FROM ShoppingCarts s WHERE s.users.id = :userId";
            Query query = session.createQuery(hql);
            query.setParameter("userId", userId);

            int result = query.executeUpdate();

            session.getTransaction().commit();

            // Kiểm tra nếu có bất kỳ hàng nào bị ảnh hưởng (được xóa)
            return result > 0;
        }catch (Exception e){
            e.printStackTrace();
            session.getTransaction().rollback();
        }finally {
            session.close();
        }
        return false;
    }


}
