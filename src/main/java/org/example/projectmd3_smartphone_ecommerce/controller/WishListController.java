package org.example.projectmd3_smartphone_ecommerce.controller;

import org.example.projectmd3_smartphone_ecommerce.dto.response.AuthenResponse;
import org.example.projectmd3_smartphone_ecommerce.dto.response.WishListResponse;
import org.example.projectmd3_smartphone_ecommerce.entity.Products;
import org.example.projectmd3_smartphone_ecommerce.entity.WishList;
import org.example.projectmd3_smartphone_ecommerce.service.CartService;
import org.example.projectmd3_smartphone_ecommerce.service.ProductService;
import org.example.projectmd3_smartphone_ecommerce.service.UserService;
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
    private ProductService productService;
    @Autowired
    private UserService userService;

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
        wishListService.addNew(WishList.builder()
                .users(userService.findByIdV2(authenResponse.getUserId()))
                .products(productService.findById(productId))
                .build());
        return "redirect:/home";
    }

    @GetMapping("delete/{wishListId}")
    public String deleteWishList(@PathVariable("wishListId") Integer wishListId){
        wishListService.deleteWishList(wishListId);
        return "redirect:/wishlist";
    }
}
