package org.example.projectmd3_smartphone_ecommerce.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/notfound")
public class PageNotFoundController {
    @GetMapping()
    String notfound(){
        return "layout/404";
    }

}
