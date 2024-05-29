package org.example.projectmd3_smartphone_ecommerce.controller;

import org.example.projectmd3_smartphone_ecommerce.dto.response.AuthenResponse;
import org.example.projectmd3_smartphone_ecommerce.service.CartService;
import org.example.projectmd3_smartphone_ecommerce.service.WishListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/wishlist")

public class WishListController {
    @Autowired
    private WishListService wishListService;

    @Autowired
    HttpSession session;


    @GetMapping()
    public String getWishListByUserId(Model model){
        AuthenResponse authenResponse = (AuthenResponse) session.getAttribute("userLogin");
        model.addAttribute("wishList", wishListService.findWishListByUserId(authenResponse.getUserId()));
        return "/Client/wishlist/wishlist";
    }
    @GetMapping("add/{productId}")
    public String addWishList(@PathVariable("productId")Integer productId, Model model){
        AuthenResponse authenResponse = (AuthenResponse) session.getAttribute("userLogin");

        return "/Client/wishlist/wishlist";
    }

    @GetMapping("delete/{wishListId}")
    public String deleteWishList(@PathVariable("wishListId") Integer wishListId){
        AuthenResponse authenResponse = (AuthenResponse) session.getAttribute("userLogin");
        wishListService.deleteWishList(wishListId);
        return "redirect:/wishlist";
    }
}
