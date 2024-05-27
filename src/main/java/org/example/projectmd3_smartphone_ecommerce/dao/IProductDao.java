package org.example.projectmd3_smartphone_ecommerce.dao;

import com.sun.org.apache.xpath.internal.operations.Bool;
import org.example.projectmd3_smartphone_ecommerce.entity.Products;

import java.util.List;

public interface IProductDao {
    List<Products> selectAllProducts(int currentPage,int size);
    Products selectProductById(int id);
    Boolean insertProduct(Products product);
    Boolean updateProduct(Products product);
    void deleteProduct(int id);
    List<Products> searchProduct(String product);
    public Long countAllProduct();
}
