package org.example.projectmd3_smartphone_ecommerce.controller;


import org.example.projectmd3_smartphone_ecommerce.dao.impl.CategoryDaoImpl;
import org.example.projectmd3_smartphone_ecommerce.dao.impl.ProductDaoImpl;
import org.example.projectmd3_smartphone_ecommerce.dto.request.ProductRequest;
import org.example.projectmd3_smartphone_ecommerce.service.impl.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.RequestMapping;


import org.example.projectmd3_smartphone_ecommerce.dao.impl.UserDaoImpl;
import org.example.projectmd3_smartphone_ecommerce.dto.request.AuthenRequest;
import org.example.projectmd3_smartphone_ecommerce.dto.request.FormLogin;
import org.example.projectmd3_smartphone_ecommerce.service.AuthenService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;


@Controller
@RequestMapping("/auth")
public class AuthenController {
    @Autowired

    ProductServiceImpl productService;
    @Autowired
    ProductDaoImpl productDao;
    @Autowired
    CategoryDaoImpl categoryDao;
    @RequestMapping("/")
    public String index() {
        return "/Client/home/home";
    }

    @RequestMapping("/dashboard")
    public String dashboard(@ModelAttribute("product") ProductRequest product,Model model, @RequestParam(defaultValue = "0") int currentPage, @RequestParam(defaultValue = "5") int size) {
        model.addAttribute("list", productService.selectAllProducts(currentPage,size));
        model.addAttribute("totalPages",Math.ceil( (double) productService.countAllProduct() / size));
        model.addAttribute("categories", categoryDao.getAll(1,3));
        return "/Admin/dashboard/dashboard";
    }

    @PostMapping("/addPro")
    public String addPro(@ModelAttribute("product") ProductRequest product,HttpServletRequest request){
        productService.insertProduct(product,request);
        return "redirect:/dashboard";
    }

    @GetMapping("/editInit/{id}")
    public String editInit(Model model, @PathVariable int id){
        model.addAttribute("product", productDao.findById(id));
        model.addAttribute("categories", categoryDao.getAll(1,3));
        return "/Admin/dashboard/edit";
    }

    @PostMapping("/edit")
    public String edit(@ModelAttribute("product") ProductRequest product,HttpServletRequest request){
        productService.updateProduct(product,request);
        return "redirect:/dashboard";
    }

    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable int id){
        productService.deleteProduct(id);
        return "redirect:/dashboard";
    }

    private UserDaoImpl userDao;
    @Autowired
    private HttpSession session;
    @Autowired
    private AuthenService authenService;



    @GetMapping("/register")
    public ModelAndView formRegister() {
        return new ModelAndView("Admin/authen/register", "user", new AuthenRequest());
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String doRegister(@ModelAttribute("user") @Valid AuthenRequest user, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("user", user);
            return "Admin/authen/register";
        } else {
            authenService.register(user);
            return "redirect:/auth/login";
        }
    }


    @GetMapping("/login")
    public String formLogin(Model model) {
        model.addAttribute("formLogin", new FormLogin());
        return "/Admin/authen/login";
    }

    @PostMapping("/login")
    public String doLogin(@ModelAttribute FormLogin formLogin, Model model) {
        if (authenService.login(formLogin)) {
            return "redirect:/";
        } else {
            model.addAttribute("err", "Sai email hoặc mật khẩu!");
            model.addAttribute("formLogin", formLogin);
            return "/Admin/authen/login";
        }
    }
    @RequestMapping("/logout")
    public String doLogout() {
        session.invalidate();
        return "redirect:/";
    }






}

