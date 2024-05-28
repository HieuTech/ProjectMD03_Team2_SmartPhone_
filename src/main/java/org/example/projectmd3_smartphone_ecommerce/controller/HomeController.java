package org.example.projectmd3_smartphone_ecommerce.controller;

import org.example.projectmd3_smartphone_ecommerce.dao.impl.ProductDaoImpl;
import org.example.projectmd3_smartphone_ecommerce.dto.response.AuthenResponse;
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

        session.setAttribute("user", userService.findByIdV2(1));
        model.addAttribute("productList", productService.findAllV2());
        return "Client/home/home";
    }
}
