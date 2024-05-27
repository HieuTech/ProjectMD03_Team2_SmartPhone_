package org.example.projectmd3_smartphone_ecommerce.service;

import org.example.projectmd3_smartphone_ecommerce.dao.ICategoryDao;
import org.example.projectmd3_smartphone_ecommerce.dao.impl.CategoryDaoImpl;
import org.example.projectmd3_smartphone_ecommerce.dto.request.CategoryRequest;
import org.example.projectmd3_smartphone_ecommerce.entity.Categories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriesService implements ICategoryDao {
    @Autowired
    private CategoryDaoImpl categoryDao;

    @Override
    public List<Categories> showAllCategory() {
        return null;
    }

    @Override
    public Boolean addCategory(Categories category) {
        return null;
    }

    @Override
    public Boolean updateCategory(Categories category) {
        return null;
    }

    @Override
    public void deleteCategory(int id) {

    }

    @Override
    public Categories getCategoryByID(int id) {
        return null;
    }
}
