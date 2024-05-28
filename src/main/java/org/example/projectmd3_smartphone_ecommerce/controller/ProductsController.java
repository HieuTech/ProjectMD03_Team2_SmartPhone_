package org.example.projectmd3_smartphone_ecommerce.controller;


import org.example.projectmd3_smartphone_ecommerce.dao.impl.ProductDaoImpl;
import org.example.projectmd3_smartphone_ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/products")
public class ProductsController {
    @Autowired
    private ProductDaoImpl productDao;
    @Autowired
    private ProductService productService;

    @GetMapping("/detail/{productId}")
    public String getDetail(@PathVariable("productId") Integer productId, Model model){

        model.addAttribute("productList", productService.findAllV2());
        model.addAttribute("product",this.productService.findById(productId));
        System.out.println(this.productDao.findByIdV2(productId).getName());
        return "/Client/products/productDetail";
    }


}
