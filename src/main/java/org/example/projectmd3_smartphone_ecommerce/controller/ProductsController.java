package org.example.projectmd3_smartphone_ecommerce.controller;

import org.example.projectmd3_smartphone_ecommerce.dao.impl.CategoryDaoImpl;
import org.example.projectmd3_smartphone_ecommerce.entity.Products;
import org.example.projectmd3_smartphone_ecommerce.service.ProductService;
import org.example.projectmd3_smartphone_ecommerce.service.impl.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.example.projectmd3_smartphone_ecommerce.dao.impl.ProductDaoImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductsController {
    @Autowired
    private ProductService productService;
    @Autowired
    ProductServiceImpl productService2;
    @Autowired
    CategoryDaoImpl categoryDao;

//    @GetMapping("/detail/{productId}")
//    public String getDetail(@PathVariable("productId") Integer productId, Model model) {
//        model.addAttribute("product", this.productService.findById(productId));
////        model.addAttribute("product",this.productDao.findByIdV2(productId));
////        System.out.println(this.productDao.findByIdV2(productId).getName());
//        return null;
//    }
    @Autowired
    private ProductDaoImpl productDao;


    @GetMapping("/detail/{productId}")
    public String getDetail(@PathVariable("productId") Integer productId, Model model) {

        model.addAttribute("product", this.productService.findById(productId));
        List<Products> productsList = new ArrayList<>();
        for (Products product : productService.findAllV2()) {
            if(product.getCategories().getId() == productService.findById(productId).getCategories().getId()){
                productsList.add(product);
            }
        }
        model.addAttribute("productList", productsList);

        System.out.println(this.productDao.findByIdV2(productId).getName());
        return "/Client/products/productDetail";
    }
}
