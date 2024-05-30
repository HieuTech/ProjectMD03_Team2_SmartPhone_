package org.example.projectmd3_smartphone_ecommerce.service.impl;

import org.example.projectmd3_smartphone_ecommerce.dao.impl.BannerDaoImpl;
import org.example.projectmd3_smartphone_ecommerce.dto.request.BannerRequest;
import org.example.projectmd3_smartphone_ecommerce.service.IBannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BannerService implements IBannerService {
    @Autowired
    private BannerDaoImpl bannerDao;
    @Override
    public boolean updateBanner(BannerRequest request) {
        return bannerDao.update(request, request.getId());
    }
}
