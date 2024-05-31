package org.example.projectmd3_smartphone_ecommerce.controller;

import org.example.projectmd3_smartphone_ecommerce.dao.impl.CategoryDaoImpl;
import org.example.projectmd3_smartphone_ecommerce.dto.response.AuthenResponse;
import org.example.projectmd3_smartphone_ecommerce.entity.Comment;
import org.example.projectmd3_smartphone_ecommerce.entity.Products;
import org.example.projectmd3_smartphone_ecommerce.service.AuthenService;
import org.example.projectmd3_smartphone_ecommerce.service.ProductService;

import org.example.projectmd3_smartphone_ecommerce.service.UserService;

import org.example.projectmd3_smartphone_ecommerce.service.VoucherService;

import org.example.projectmd3_smartphone_ecommerce.service.impl.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.example.projectmd3_smartphone_ecommerce.dao.impl.ProductDaoImpl;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
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
    @Autowired
    VoucherService voucherService;

    //    @GetMapping("/detail/{productId}")
//    public String getDetail(@PathVariable("productId") Integer productId, Model model) {
//        model.addAttribute("product", this.productService.findById(productId));
////        model.addAttribute("product",this.productDao.findByIdV2(productId));
////        System.out.println(this.productDao.findByIdV2(productId).getName());
//        return null;
//    }
    @Autowired
    private ProductDaoImpl productDao;
    @Autowired
    UserService userService;
    @Autowired
    AuthenService authenService;

    @GetMapping("/detail/{productId}")
    public String getDetail(@PathVariable("productId") Integer productId, Model model, @RequestParam(defaultValue = "0") int currentPage,
                            @RequestParam(defaultValue = "4") int size) {
        model.addAttribute("product", this.productService.findById(productId));
        model.addAttribute("totalPages", Math.ceil((double) productService2.countAllProduct() / size));
        model.addAttribute("comments", userService.getComments(productId, currentPage, size));

        List<Products> productsList = new ArrayList<>();
        for (Products product : productService.findAllV2()) {
            if (product.getCategories().getId() == productService.findById(productId).getCategories().getId()) {
                productsList.add(product);
            }
        }
        model.addAttribute("productList", productsList);
        AuthenResponse authenResponse = (AuthenResponse) session.getAttribute("userLogin");
        model.addAttribute("user", "a");
        System.out.println(this.productDao.findByIdV2(productId).getName());
        return "/Client/products/productDetail";
    }

    @Autowired
    HttpSession session;
    @PostMapping("/addComment/{id}")
    public String addComment(@RequestParam("commentText") String commentText, @RequestParam("Rate") Double rate, @PathVariable("id") Integer productId) {
        AuthenResponse authenResponse = (AuthenResponse) session.getAttribute("userLogin");
        Comment comment = new Comment(authenResponse.getUserId(), productId, commentText, rate, authenResponse.getUserName());
        userService.addComment(comment);
        return "redirect:/products/detail/" + productId;
    }
}
