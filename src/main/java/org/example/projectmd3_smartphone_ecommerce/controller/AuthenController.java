package org.example.projectmd3_smartphone_ecommerce.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller

public class AuthenController {
    @RequestMapping("/")
    public String index() {
        return "/Client/home/home";
    }

