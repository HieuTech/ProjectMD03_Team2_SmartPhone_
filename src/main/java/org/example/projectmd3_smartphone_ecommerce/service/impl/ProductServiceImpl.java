package org.example.projectmd3_smartphone_ecommerce.service.impl;

import org.example.projectmd3_smartphone_ecommerce.dao.impl.CategoryDaoImpl;
import org.example.projectmd3_smartphone_ecommerce.dao.impl.ProductDaoImpl;
import org.example.projectmd3_smartphone_ecommerce.dto.request.ProductRequest;
import org.example.projectmd3_smartphone_ecommerce.entity.Products;
import org.example.projectmd3_smartphone_ecommerce.service.IProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductServiceImpl implements IProductService {
    @Autowired
    CategoryDaoImpl categoryDao;
    @Autowired
    ProductDaoImpl productDao;
    @Autowired
    private ModelMapper mapper;

    @Override
    public List<Products> selectAllProducts(int currentPage, int size) {
        return productDao.getAll(currentPage, size);
    }

    @Override
    public Products selectProductById(int id) {
        return productDao.findById(id);
    }

    @Override
    public Boolean insertProduct(ProductRequest product, HttpServletRequest request) {
        Products products = mapper.map(product, Products.class);
        String path = request.getServletContext().getRealPath("/images");
        File file1 = new File(path);
        if (!file1.exists()) {
            file1.mkdir();
        }
        MultipartFile imgFile = product.getImage();
        if (imgFile != null) {
            String fileName = imgFile.getOriginalFilename();
            try {
                File destination = new File(file1.getAbsolutePath() + "/" + fileName);
                if (!destination.exists()) {
                    FileCopyUtils.copy(imgFile.getBytes(), destination);
                }
                products.setImage(fileName);
                products.setCategories(categoryDao.findById(product.getCategories()));
                productDao.addNew(product);
                return true;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return false;
    }

    public Boolean insertProducts1(ProductRequest product, HttpServletRequest request) {
        Products products = mapper.map(product, Products.class);
        String path = request.getServletContext().getRealPath("/images");
        File file1 = new File(path);
        if (!file1.exists()) {
            file1.mkdir();
        }
        MultipartFile imgFile = product.getImage();
        if (imgFile != null) {
            String fileName = imgFile.getOriginalFilename();
            try {
                File destination = new File(file1.getAbsolutePath() + "/" + fileName);
                if (!destination.exists()) {
                    FileCopyUtils.copy(imgFile.getBytes(), destination);
                }
                products.setImage(fileName);
                products.setCategories(categoryDao.findById(product.getCategories()));
                productDao.addNew2(products);
                return true;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return false;
    }

    @Override
    public Boolean updateProduct(ProductRequest product, HttpServletRequest request) {
        Products products = mapper.map(product, Products.class);
        String path = request.getServletContext().getRealPath("/images");
        File file1 = new File(path);
        if (!file1.exists()) {
            file1.mkdir();
        }
        MultipartFile imgFile = product.getImage();
        if (imgFile != null && !imgFile.isEmpty()) {
            String fileName = imgFile.getOriginalFilename();
            try {
                File destination = new File(file1.getAbsolutePath() + "/" + fileName);
                if (!destination.exists()) {
                    FileCopyUtils.copy(imgFile.getBytes(), destination);
                }
                products.setImage(fileName);
                products.setCategories(categoryDao.findById(product.getCategories()));
                productDao.update2(products);
                return true;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            products.setImage(productDao.findById(products.getId()).getImage());
            products.setCategories(categoryDao.findById(product.getCategories()));
            productDao.update2(products);
            return true;
        }

    }

    @Override
    public void deleteProduct(int id) {
        productDao.delete(id);
    }

    @Override
    public List<Products> searchProduct(String product) {
        List<Products> productss = new ArrayList<>();
        for (Products products : productDao.searchProduct(product)) {
            productss.add(products);
        }
        return productss;
    }

    @Override
    public Long countAllProduct() {
        return productDao.countAllProduct();
    }

}
