package org.example.projectmd3_smartphone_ecommerce.controller;

import org.example.projectmd3_smartphone_ecommerce.dto.request.CartRequest;
import org.example.projectmd3_smartphone_ecommerce.dto.response.AuthenResponse;
import org.example.projectmd3_smartphone_ecommerce.dto.response.CartResponse;
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
        AuthenResponse authenResponse = (AuthenResponse) session.getAttribute("userLogin");

        if (authenResponse != null) {
            modelMap.addAttribute("cartList", this.cartService.findAllCartByUserId(authenResponse.getUserId()));

            double totalPrice = 0;
            for (CartResponse cartResponse : this.cartService.findAllCartByUserId(authenResponse.getUserId())) {
                totalPrice += cartResponse.getProductPrice();
                modelMap.addAttribute("totalPrice", totalPrice);
            }
            return "/Client/addtocart/addtocart";

        } else {
            return "redirect:/notfound";
        }
    }

    @GetMapping("/delete/{cartId}")
    public String deleteCart(@PathVariable("cartId") Integer cartId, ModelMap modelMap) {
        AuthenResponse authenResponse = (AuthenResponse) session.getAttribute("userLogin");

        modelMap.addAttribute("cartList", this.cartService.findAllCartByUserId(authenResponse.getUserId()));
        cartService.deleteCart(cartId);

        authenResponse.setCartQuantity(this.cartService.findAllCartByUserId(authenResponse.getUserId()).size());
        session.setAttribute("userLogin", authenResponse);
        return "redirect:/Cart";
    }

    @GetMapping("/add/{productId}")
    public String addToCart(@PathVariable("productId") Integer productId, ModelMap modelMap) {
        AuthenResponse authenResponse = (AuthenResponse) session.getAttribute("userLogin");

        cartService.addToCart(CartRequest.builder()
                .orderQuantity(1).productId(productId).userId(authenResponse.getUserId())
                .build());

        authenResponse.setCartQuantity(this.cartService.findAllCartByUserId(authenResponse.getUserId()).size());
        session.setAttribute("userLogin", authenResponse);
        modelMap.addAttribute("cartList", this.cartService.findAllCartByUserId(authenResponse.getUserId()));
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
