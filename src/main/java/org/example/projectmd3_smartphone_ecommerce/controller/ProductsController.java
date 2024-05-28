package org.example.projectmd3_smartphone_ecommerce.controller;


import org.example.projectmd3_smartphone_ecommerce.dao.impl.CategoryDaoImpl;
import org.example.projectmd3_smartphone_ecommerce.service.ProductService;
import org.example.projectmd3_smartphone_ecommerce.service.impl.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;



@Controller
@RequestMapping("/products")
public class ProductsController {
    @Autowired
    private ProductService productService;
    @Autowired
    ProductServiceImpl productService2;
    @Autowired
    CategoryDaoImpl categoryDao;

    @GetMapping("/detail/{productId}")
    public String getDetail(@PathVariable("productId") Integer productId, Model model){
        model.addAttribute("product",this.productService.findById(productId));
//        model.addAttribute("product",this.productDao.findByIdV2(productId));
//        System.out.println(this.productDao.findByIdV2(productId).getName());
        return "/Client/products/productDetail";
    }



}
