package org.example.projectmd3_smartphone_ecommerce.dao;

import org.example.projectmd3_smartphone_ecommerce.dto.request.ProductRequest;
import org.example.projectmd3_smartphone_ecommerce.entity.Products;

import java.util.List;

public interface IProductDao extends IGenericDao<Products, ProductRequest, Integer>{

    List<Products> findProductByName(String productName);

}
