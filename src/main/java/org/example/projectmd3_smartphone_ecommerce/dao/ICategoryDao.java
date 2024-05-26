package org.example.projectmd3_smartphone_ecommerce.dao;

import org.example.projectmd3_smartphone_ecommerce.entity.Categories;

import java.util.List;

public interface ICategoryDao {
    List<Categories> getAllCategory();
    Categories findById(Integer categoryId);

}
