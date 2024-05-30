package org.example.projectmd3_smartphone_ecommerce.dao.impl;

import org.example.projectmd3_smartphone_ecommerce.dao.IWishListDao;
import org.example.projectmd3_smartphone_ecommerce.dto.response.AuthenResponse;
import org.example.projectmd3_smartphone_ecommerce.entity.Orders;
import org.example.projectmd3_smartphone_ecommerce.entity.WishList;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.servlet.http.HttpSession;
import java.util.List;

@Repository
public class WishListDaoImpl implements IWishListDao {
    @Autowired
    private SessionFactory sessionFactory;
    @Autowired
    private HttpSession httpSession;

    @Override
    public List<WishList> getAllV2() {
        Session session = sessionFactory.openSession();

        try {
            List<WishList> list = session.createQuery("from WishList ").list();
            return list;
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            session.close();
        }
        return null;
    }

    @Override
    public WishList findById(Integer id) {
        Session session = sessionFactory.openSession();

        try {
            return session.get(WishList.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return null;
    }

    @Override
    public List<WishList> findByUserId(Integer id) {
        Session session = sessionFactory.openSession();
        try {
            String hql = "SELECT o from WishList o where o.users.id = :userId";
            List<WishList> wishLists = (List<WishList>) session.createQuery(hql).setParameter("userId", id).list();
            return wishLists;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return null;
    }

    @Override
    public boolean addNew(WishList wishList) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.save(wishList);
            session.getTransaction().commit();
            AuthenResponse authenResponse = (AuthenResponse) httpSession.getAttribute("userLogin");
            if (authenResponse != null) {
                authenResponse.setWishListQuantity(this.findByUserId(authenResponse.getUserId()).size());
                httpSession.setAttribute("userLogin", authenResponse
                );
            }
            return true;
        } catch (Exception e) {

            e.printStackTrace();
            //neu that bai phai khoi phuc lai du lieu
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return false;
    }

    @Override
    public boolean update(WishList object) {
        return false;
    }

    @Override

    public boolean deleteWishList(Integer wishListId) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.delete(this.findById(wishListId));
            session.getTransaction().commit();

            AuthenResponse authenResponse = (AuthenResponse) httpSession.getAttribute("userLogin");
            if (authenResponse != null) {
                authenResponse.setWishListQuantity(this.findByUserId(authenResponse.getUserId()).size());
                httpSession.setAttribute("userLogin", authenResponse
                );
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return false;
    }
}
