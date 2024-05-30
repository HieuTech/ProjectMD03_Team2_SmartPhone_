package org.example.projectmd3_smartphone_ecommerce.dao.impl;

import org.example.projectmd3_smartphone_ecommerce.dao.IVoucherDao;
import org.example.projectmd3_smartphone_ecommerce.dto.response.AuthenResponse;
import org.example.projectmd3_smartphone_ecommerce.entity.Vouchers;
import org.example.projectmd3_smartphone_ecommerce.entity.WishList;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class VoucherDaoImpl implements IVoucherDao {
    @Autowired
    private SessionFactory sessionFactory;
    @Override
    public List<Vouchers> getAllV2() {
        Session session = sessionFactory.openSession();

        try {
            List<Vouchers> list = session.createQuery("from Vouchers ").list();
            return list;
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            session.close();
        }
        return null;
    }

    @Override
    public Vouchers findById(Integer id) {
        Session session = sessionFactory.openSession();

        try {
            return session.get(Vouchers.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return null;
    }

    @Override
    public List<Vouchers> findByUserId(Integer id) {
        Session session = sessionFactory.openSession();
        try {
            String hql = "SELECT o from Vouchers o where o.users.id = :userId";
            List<Vouchers> vouchers = (List<Vouchers>) session.createQuery(hql).setParameter("userId", id).list();
            return vouchers;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return null;
    }

    @Override
    public boolean addNew(Vouchers vouchers) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.save(vouchers);
            session.getTransaction().commit();
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
    public boolean update(Vouchers object) {
        return false;
    }

    @Override
    public boolean deleteVouchers(Integer voucherId) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.delete(this.findById(voucherId));
            session.getTransaction().commit();
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
