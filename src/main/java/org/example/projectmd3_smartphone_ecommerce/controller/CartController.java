package org.example.projectmd3_smartphone_ecommerce.controller;

import org.example.projectmd3_smartphone_ecommerce.dto.request.CartRequest;
import org.example.projectmd3_smartphone_ecommerce.entity.Users;
import org.example.projectmd3_smartphone_ecommerce.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/Cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    HttpSession session;


    @GetMapping()
    public String getAllCart(ModelMap modelMap) {
        Users users = (Users) session.getAttribute("user");
        if (users != null) {
            modelMap.addAttribute("cartList", this.cartService.findAllCartByUserId(1));
            return "/Client/addtocart/addtocart";

        } else {
            return "redirect:/notfound";
        }
    }

    @GetMapping("/add/{productId}")
    public String addToCart(@PathVariable("productId") Integer productId, ModelMap modelMap) {
        Users users = (Users) session.getAttribute("user");

        modelMap.addAttribute("cartList", this.cartService.findAllCartByUserId(1));



        if (cartService.addToCart(CartRequest.builder()
                .orderQuantity(1).productId(productId).userId(users.getId())
                .build())) {
            modelMap.addAttribute("message", "Add Successfully");
        }
        return "redirect:/Cart";
    }

    @GetMapping("/plus/{cartId}")
    public String plusCart(@PathVariable("cartId") Integer cartId, ModelMap modelMap) {
        Users users = (Users) session.getAttribute("user");

        modelMap.addAttribute("cartList", this.cartService.findAllCartByUserId(1));
        cartService.updateCart(CartRequest.builder()
                .orderQuantity(cartService.findCartById(cartId).getQuantity() - 1).productId(cartService.findCartById(cartId).getProductId()).userId(users.getId())
                .build());
        return "redirect:/Cart";
    }

    @GetMapping("/minus/{cartId}")
    public String minusCart(@PathVariable("cartId") Integer cartId, ModelMap modelMap) {
        Users users = (Users) session.getAttribute("user");

        modelMap.addAttribute("cartList", this.cartService.findAllCartByUserId(1));
        cartService.updateCart(CartRequest.builder()
                .orderQuantity(cartService.findCartById(cartId).getQuantity() - 1).productId(cartService.findCartById(cartId).getProductId()).userId(users.getId())
                .build());
        return "redirect:/Cart";
    }


}
