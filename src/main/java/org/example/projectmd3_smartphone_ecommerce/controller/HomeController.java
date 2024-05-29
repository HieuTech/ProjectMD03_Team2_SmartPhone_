package org.example.projectmd3_smartphone_ecommerce.controller;

import org.example.projectmd3_smartphone_ecommerce.dao.impl.ProductDaoImpl;
import org.example.projectmd3_smartphone_ecommerce.dto.request.ProductRequest;

import org.example.projectmd3_smartphone_ecommerce.dto.response.AuthenResponse;
import org.example.projectmd3_smartphone_ecommerce.service.OrderService;
import org.example.projectmd3_smartphone_ecommerce.service.ProductService;
import org.example.projectmd3_smartphone_ecommerce.service.UserService;
import org.example.projectmd3_smartphone_ecommerce.service.WishListService;
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
    private OrderService orderService;
    @Autowired
    private WishListService wishListService;
    private
    @Autowired
    HttpSession session;
//    @GetMapping("")
//    public String home(Model model,@RequestParam(defaultValue = "0") int currentPage, @RequestParam(defaultValue = "5") int size){
//        session.setAttribute("user", userService.findByIdV2(1));
//        model.addAttribute("productList", productService2.selectAllProducts(currentPage,size));
//        model.addAttribute("totalPages",Math.ceil( (double) productService2.countAllProduct() / size));
//        model.addAttribute("title", "Latest Products");
//        return "Client/home/home";
//    }

    @GetMapping
    public String home(Model model){
        AuthenResponse authenResponse = (AuthenResponse) session.getAttribute("userLogin");
        model.addAttribute("productList", productService.findAllV2());
        model.addAttribute("userLogin", authenResponse);

        return "Client/home/home";
    }

    @PostMapping("/search")
    public String search(@RequestParam("keyword") String keyword, Model model) {
        session.setAttribute("user", userService.findByIdV2(1));
        model.addAttribute("title", "Search Products");
        model.addAttribute("productList", productService2.searchProduct(keyword));

        return "Client/home/home";
    }

@GetMapping("/Filter")
    public String Filter( Model model) {

        return "Client/home/Filter";
}

}
