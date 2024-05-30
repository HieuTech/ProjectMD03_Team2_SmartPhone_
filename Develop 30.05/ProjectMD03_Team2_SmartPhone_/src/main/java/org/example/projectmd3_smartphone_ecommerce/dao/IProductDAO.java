package org.example.projectmd3_smartphone_ecommerce.dao;

import org.example.projectmd3_smartphone_ecommerce.dto.request.ProductRequest;
import org.example.projectmd3_smartphone_ecommerce.entity.Products;

import java.util.List;

public interface IProductDAO extends IGenericDao<Products, ProductRequest,Integer>{
 public boolean addNew2(Products p);
 public boolean update2(Products p);
 List<Products> sorf(String sorf, Integer currentPage, Integer size);
}
