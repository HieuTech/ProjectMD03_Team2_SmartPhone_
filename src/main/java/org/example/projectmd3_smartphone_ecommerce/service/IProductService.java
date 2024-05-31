package org.example.projectmd3_smartphone_ecommerce.service;

import org.example.projectmd3_smartphone_ecommerce.dto.request.ProductRequest;
import org.example.projectmd3_smartphone_ecommerce.entity.Products;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface IProductService {
    List<Products> selectAllProducts(int currentPage, int size);
    Products selectProductById(int id);
    Boolean insertProduct(ProductRequest product);
    Boolean updateProduct(ProductRequest product);
    boolean deleteProduct(int id);
    List<Products> searchProduct(String product);
    public Long countAllProduct();
}
