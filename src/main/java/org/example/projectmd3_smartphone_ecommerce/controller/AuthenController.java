package org.example.projectmd3_smartphone_ecommerce.controller;




import org.example.projectmd3_smartphone_ecommerce.service.impl.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.bind.annotation.RequestMapping;



import org.example.projectmd3_smartphone_ecommerce.dto.request.AuthenRequest;
import org.example.projectmd3_smartphone_ecommerce.dto.request.FormLogin;

import org.example.projectmd3_smartphone_ecommerce.entity.Users;
import org.example.projectmd3_smartphone_ecommerce.service.AuthenService;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;


@Controller
@RequestMapping(value = {"/", "/auth"})
public class AuthenController {

    @Autowired
    private HttpSession session;

    @Autowired
    private AuthenService authenService;




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

