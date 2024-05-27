package org.example.projectmd3_smartphone_ecommerce.service;

import org.example.projectmd3_smartphone_ecommerce.dto.request.ProductRequest;
import org.example.projectmd3_smartphone_ecommerce.entity.Products;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface IProductService {
    List<Products> selectAllProducts(int currentPage, int size);
    Products selectProductById(int id);
    Boolean insertProduct(ProductRequest product, HttpServletRequest request);
    Boolean updateProduct(ProductRequest product,HttpServletRequest request);
    void deleteProduct(int id);
    List<ProductRequest> searchProduct(String product);
    public Long countAllProduct();
}
