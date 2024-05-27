package org.example.projectmd3_smartphone_ecommerce.controller;


import org.example.projectmd3_smartphone_ecommerce.dao.impl.ProductDaoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/products")
public class ProductsController {
    @Autowired
    private ProductDaoImpl productDao;

    @GetMapping("/detail/{productId}")
    public String getDetail(@PathVariable("productId") Integer productId, Model model){

        model.addAttribute("product",this.productDao.findById(productId));

        return "/Client/products/productDetail";
    }




}
