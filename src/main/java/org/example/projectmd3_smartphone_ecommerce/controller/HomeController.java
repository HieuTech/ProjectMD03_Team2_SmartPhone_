package org.example.projectmd3_smartphone_ecommerce.controller;

import org.example.projectmd3_smartphone_ecommerce.dao.impl.ProductDaoImpl;
import org.example.projectmd3_smartphone_ecommerce.service.ProductService;
import org.example.projectmd3_smartphone_ecommerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/home")
public class HomeController {
    @Autowired
    private ProductService productService;
    @Autowired
    private UserService userService;
    @Autowired
    HttpSession session;
    @GetMapping
    public String home(Model model){
        session.setAttribute("user", userService.findUserById(1));
        model.addAttribute("productList", productService.findAll(1,3 ));

        return "Client/home/home";
    }
}
