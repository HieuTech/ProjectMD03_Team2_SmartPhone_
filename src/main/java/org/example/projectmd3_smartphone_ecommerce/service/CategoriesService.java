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
    public List<Categories> getAll(Integer currentPage, Integer size) {
        return null;
    }

    @Override
    public Categories findById(Integer id) {
        return null;
    }

    @Override
    public boolean addNew(CategoryRequest object) {
        return false;
    }

    @Override
    public boolean update(CategoryRequest object, Integer id) {
        return false;
    }

    @Override
    public boolean delete(Integer id) {
        return false;
    }
}
