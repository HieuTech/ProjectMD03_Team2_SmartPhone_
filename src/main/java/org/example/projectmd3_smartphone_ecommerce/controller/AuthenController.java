package org.example.projectmd3_smartphone_ecommerce.controller;


import org.example.projectmd3_smartphone_ecommerce.dao.impl.CategoryDaoImpl;
import org.example.projectmd3_smartphone_ecommerce.dto.request.ProductRequest;
import org.example.projectmd3_smartphone_ecommerce.service.MailService;
import org.example.projectmd3_smartphone_ecommerce.service.impl.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.RequestMapping;


import org.example.projectmd3_smartphone_ecommerce.dao.impl.ProductDaoImpl;
import org.example.projectmd3_smartphone_ecommerce.dao.impl.UserDaoImpl;
import org.example.projectmd3_smartphone_ecommerce.dto.request.AuthenRequest;
import org.example.projectmd3_smartphone_ecommerce.dto.request.FormLogin;
import org.example.projectmd3_smartphone_ecommerce.dto.request.ProductRequest;
import org.example.projectmd3_smartphone_ecommerce.entity.Address;
import org.example.projectmd3_smartphone_ecommerce.entity.Users;
import org.example.projectmd3_smartphone_ecommerce.service.AuthenService;
import org.springframework.validation.BindingResult;

import org.example.projectmd3_smartphone_ecommerce.service.impl.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;


@Controller
@RequestMapping(value = {"/", "/auth"})
public class AuthenController {


    @Autowired

    private UserDaoImpl userDao;
    @Autowired
    private HttpSession session;
    @Autowired
    ProductServiceImpl productService;
    @Autowired
    private AuthenService authenService;

    @Autowired
    ProductServiceImpl productService2;
    @Autowired
    CategoryDaoImpl categoryDao;




    @RequestMapping("/dashboard")
    public String dashboard(@ModelAttribute("product") ProductRequest product, Model model, @RequestParam(defaultValue = "0") int currentPage, @RequestParam(defaultValue = "5") int size) {
        model.addAttribute("list", productService2.selectAllProducts(currentPage,size));
        model.addAttribute("totalPages",Math.ceil( (double) productService2.countAllProduct() / size));
        model.addAttribute("categories", categoryDao.getAll(0,100));
        return "/Admin/dashboard/dashboard";
    }
    @PostMapping("/addPro")
    public String addPro(@ModelAttribute("product") ProductRequest product, HttpServletRequest request){
        productService2.insertProducts1(product,request);
        return "redirect:/auth/dashboard";
    }

    @PostMapping("/sorf")
    public String search(@ModelAttribute("product") ProductRequest product, Model model,
                         @RequestParam(defaultValue = "0") int currentPage,
                         @RequestParam(defaultValue = "5") int size,
                         @RequestParam(name = "sortBy", required = false) String sortBy) {
        // Use sortBy for sorting logic if necessary
        model.addAttribute("list", productService.soft(sortBy,currentPage, size)); // Pass sortBy to the service if needed
        model.addAttribute("totalPages", Math.ceil((double) productService2.countAllProduct() / size));
        model.addAttribute("categories", categoryDao.getAll(0, 100));
        return "/Admin/dashboard/dashboard";
    }


    @GetMapping("/editInit/{id}")
    public String editInit(Model model, @PathVariable int id){
        model.addAttribute("product", productService2.selectProductById(id));
        model.addAttribute("categories", categoryDao.getAll(0,100));
        return "/Admin/dashboard/editProduct";
    }

    @PostMapping("/edit")
    public String edit(@ModelAttribute("product") ProductRequest product,HttpServletRequest request){
        productService2.updateProduct(product,request);
        return "redirect:/auth/dashboard";
    }


    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable int id){
        productService2.deleteProduct(id);
        return "redirect:/auth/dashboard";
    }



    @GetMapping("/register")
    public ModelAndView formRegister() {
        return new ModelAndView("Client/authen/register", "user", new AuthenRequest());
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String doRegister(@ModelAttribute("user") @Valid AuthenRequest user, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("user", user);
            return "Client/authen/register";
        } else {
            authenService.register(user);
            return "redirect:/auth";
        }
    }


    @GetMapping()
    public String formLogin(Model model) {
        model.addAttribute("formLogin", new FormLogin());
//        return "Admin/authen/login";
        return "/Client/authen/login";
    }

    @PostMapping("/login")
    public String doLogin(@ModelAttribute FormLogin formLogin, Model model) {
        if (authenService.login(formLogin)) {
            return "redirect:/home";
        } else {
            model.addAttribute("err", "Sai email hoặc mật khẩu!");
            model.addAttribute("formLogin", formLogin);
            return "/Client/authen/login";
        }
    }
    @RequestMapping("/logout")
    public String doLogout() {
        session.invalidate();
        return "redirect:/";
    }

    @GetMapping("/profile")
    public String viewProfile(Model model){
        Users userLogin = (Users) session.getAttribute("userLogin");
        if (userLogin == null) {
            return "redirect:/auth/login";
        }
        model.addAttribute("user", userLogin);
//        model.addAttribute("address", userLogin.getAddress());
        return "/Client/authen/profile";
    }




}

