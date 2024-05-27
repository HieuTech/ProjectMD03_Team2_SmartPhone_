package org.example.projectmd3_smartphone_ecommerce.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/orders")
public class OrderController {

    @GetMapping()
    public String orders(){

        return "Client/orders/checkout";
    }

    @PostMapping("/checkout")
    public String checkout(){


        return "redirect:/orders/success";
    }

    @GetMapping("/success")
    public String success(){

        return "Client/orders/success";
    }



}
