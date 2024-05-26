package org.example.projectmd3_smartphone_ecommerce.dao.impl;

import org.example.projectmd3_smartphone_ecommerce.dao.IProductDao;
import org.example.projectmd3_smartphone_ecommerce.entity.Products;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductDaoImpl implements IProductDao {
    @Override
    public List<Products> getAllProduct() {
        return null;
    }

    @Override
    public Products getProductById(Integer productId) {
        return null;
    }

    @Override
    public boolean insertProduct(Products products) {
        return false;
    }

    @Override
    public boolean updateProduct(Products products) {
        return false;
    }

    @Override
    public boolean deleteProduct(Integer productId) {
        return false;
    }

    @Override
    public List<Products> findProductByName(String productName) {
        return null;
    }
}
