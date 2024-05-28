package org.example.projectmd3_smartphone_ecommerce.controller;

import org.example.projectmd3_smartphone_ecommerce.dao.impl.ProductDaoImpl;
import org.example.projectmd3_smartphone_ecommerce.service.ProductService;
import org.example.projectmd3_smartphone_ecommerce.service.UserService;
import org.example.projectmd3_smartphone_ecommerce.service.impl.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/home")
public class HomeController {
    @Autowired
    private ProductService productService;
    @Autowired
    private ProductServiceImpl productService2;
    @Autowired
    private UserService userService;
    @Autowired
    HttpSession session;
    @GetMapping
    public String home(Model model){
        session.setAttribute("user", userService.findByIdV2(1));
        model.addAttribute("productList", productService.findAllV2());

        model.addAttribute("title", "Latest Products");
        return "Client/home/home";
    }

    @PostMapping("/search")
    public String search(@RequestParam("keyword") String keyword, Model model) {
        model.addAttribute("title", "Search Products");
        model.addAttribute("productList", productService2.searchProduct(keyword));
//        if(productService2.searchProduct(keyword).size() == 0){
//            model.addAttribute("err","không tìm thấy");
//        }
        return "Client/home/home";
    }


}
