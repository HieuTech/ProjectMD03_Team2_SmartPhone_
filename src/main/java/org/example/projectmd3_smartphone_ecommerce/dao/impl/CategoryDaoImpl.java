package org.example.projectmd3_smartphone_ecommerce.dao.impl;

import org.example.projectmd3_smartphone_ecommerce.dao.ICategoryDao;
import org.example.projectmd3_smartphone_ecommerce.entity.Categories;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CategoryDaoImpl implements ICategoryDao {
    @Override
    public List<Categories> getAllCategory() {
        return null;
    }

    @Override
    public Categories findById(Integer categoryId) {
        return null;
    }
}
