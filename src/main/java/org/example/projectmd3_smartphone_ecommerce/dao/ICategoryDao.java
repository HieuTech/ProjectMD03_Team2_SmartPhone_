package org.example.projectmd3_smartphone_ecommerce.dao;

import org.example.projectmd3_smartphone_ecommerce.entity.Categories;
import org.example.projectmd3_smartphone_ecommerce.entity.Products;

import java.util.List;


public interface ICategoryDao {
    List<Categories> showAllCategory();
    Boolean addCategory(Categories category);
    Boolean updateCategory(Categories category);
    void deleteCategory(int id);
    Categories getCategoryByID(int id);
}
