package org.example.projectmd3_smartphone_ecommerce.service;

import org.example.projectmd3_smartphone_ecommerce.dao.impl.ProductDaoImpl;
import org.example.projectmd3_smartphone_ecommerce.dto.response.ProductResponse;
import org.example.projectmd3_smartphone_ecommerce.entity.Products;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductDaoImpl productDao;

    public List<Products> findAll(Integer currentPage, Integer size) {
        return productDao.getAll(currentPage, size);
    }


    public List<Products> findAllV2() {

        return productDao.getAllV2();

    }

    public Products findById(Integer productId) {
        return this.productDao.findById(productId);
    }
}
