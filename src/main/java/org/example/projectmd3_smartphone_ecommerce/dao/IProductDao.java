package org.example.projectmd3_smartphone_ecommerce.dao;

import org.example.projectmd3_smartphone_ecommerce.entity.Products;

import java.util.List;

public interface IProductDao {

    List<Products> getAllProduct();
    Products getProductById(Integer productId);
    boolean insertProduct(Products products);
    boolean updateProduct(Products products);
    boolean deleteProduct(Integer productId);
    List<Products> findProductByName(String productName);
}
