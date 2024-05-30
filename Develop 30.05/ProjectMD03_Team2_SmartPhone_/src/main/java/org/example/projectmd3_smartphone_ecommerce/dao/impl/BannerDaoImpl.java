package org.example.projectmd3_smartphone_ecommerce.dao.impl;

import org.example.projectmd3_smartphone_ecommerce.dao.IBannerDao;
import org.example.projectmd3_smartphone_ecommerce.dto.request.BannerRequest;
import org.example.projectmd3_smartphone_ecommerce.entity.Banner;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository

@Transactional
public class BannerDaoImpl implements IBannerDao {
    @Autowired
    private SessionFactory sessionFactory;
    @Autowired
    private ModelMapper mapper;

    @Override
    public List<Banner> getAll(Integer currentPage, Integer size) {
        return null;
    }

    @Override
    public Banner findById(Integer id) {
        return null;
    }

    @Override
    public boolean addNew(BannerRequest object) {
        return false;
    }

    @Override
    public boolean update(BannerRequest object, Integer id) {
        Banner banner = mapper.map(object, Banner.class);
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.update(banner);
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(Integer id) {
        return false;
    }
}
