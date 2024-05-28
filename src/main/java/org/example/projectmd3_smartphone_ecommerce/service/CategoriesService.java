package org.example.projectmd3_smartphone_ecommerce.service;

import org.example.projectmd3_smartphone_ecommerce.dao.ICategoryDao;
import org.example.projectmd3_smartphone_ecommerce.dao.impl.CategoryDaoImpl;
import org.example.projectmd3_smartphone_ecommerce.dto.request.CategoryRequest;
import org.example.projectmd3_smartphone_ecommerce.entity.Categories;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriesService  {
    @Autowired
    private CategoryDaoImpl categoryDao;


    public List<Categories> getAll(Integer currentPage, Integer size) {
        return categoryDao.getAll(currentPage, size);
    }


    public Categories findById(Integer id) {
        return categoryDao.findById(id);
    }

    public boolean addNew(CategoryRequest object) {
        Categories categories = new Categories(object.getId(),object.getName(),object.getDescription(),object.getStatus());
        return categoryDao.addNew2(categories);
    }

    public boolean update(CategoryRequest object, Integer id) {
        Categories categories = new Categories(object.getId(),object.getName(),object.getDescription(),object.getStatus());
        return categoryDao.update2(categories, id);
    }

    public boolean delete(Integer id) {
        return categoryDao.delete(id);
    }

    public Long countAllCategories() {
        return categoryDao.countAllCategories();
    }
}
