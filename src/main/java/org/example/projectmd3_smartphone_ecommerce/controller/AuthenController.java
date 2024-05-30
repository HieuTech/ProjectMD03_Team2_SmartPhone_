package org.example.projectmd3_smartphone_ecommerce.controller;


import org.example.projectmd3_smartphone_ecommerce.dao.impl.CategoryDaoImpl;
import org.example.projectmd3_smartphone_ecommerce.dto.request.ProductRequest;
import org.example.projectmd3_smartphone_ecommerce.service.*;
import org.example.projectmd3_smartphone_ecommerce.service.impl.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.*;

import org.springframework.web.bind.annotation.RequestMapping;


import org.example.projectmd3_smartphone_ecommerce.dao.impl.ProductDaoImpl;
import org.example.projectmd3_smartphone_ecommerce.dao.impl.UserDaoImpl;

import org.example.projectmd3_smartphone_ecommerce.dto.request.AuthenEditRequest;

import org.example.projectmd3_smartphone_ecommerce.dto.request.AuthenRequest;
import org.example.projectmd3_smartphone_ecommerce.dto.request.FormLogin;
import org.example.projectmd3_smartphone_ecommerce.dto.request.ProductRequest;
import org.example.projectmd3_smartphone_ecommerce.dto.response.AuthenResponse;
import org.example.projectmd3_smartphone_ecommerce.entity.Address;
import org.example.projectmd3_smartphone_ecommerce.entity.Users;
import org.example.projectmd3_smartphone_ecommerce.service.impl.ProductServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;


@Controller
@RequestMapping(value = {"/", "/auth"})
public class AuthenController {
    @Autowired
    private HttpSession session;
    @Autowired
    private AuthenService authenService;
    @Autowired
    private AddressService addressService;
    @Autowired
    ProductServiceImpl productService2;
    @Autowired
    private ProductService productService;
    @Autowired
    CategoryDaoImpl categoryDao;
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private UserDaoImpl userDao;
    @Autowired
    private CartService cartService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private WishListService wishListService;






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
        model.addAttribute("list", productService2.soft(sortBy,currentPage, size)); // Pass sortBy to the service if needed
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
            model.addAttribute("errors", bindingResult);

            return "Client/authen/register";
        } else {
            authenService.register(user);
            return "redirect:/auth";
        }
    }


    @GetMapping()
    public String formLogin(Model model, HttpServletRequest request) {
        AuthenResponse authenResponse = (AuthenResponse) session.getAttribute("userLogin");
        model.addAttribute("message", "");

        model.addAttribute("productList", productService.findAllV2());

        Cookie[] cookies = request.getCookies();
        if (cookies != null) {

            String userEmail = null;
            boolean isLoggedIn = false;

            for (Cookie cookie : cookies) {
                if ("loginStatus".equals(cookie.getName()) && "true".equals(cookie.getValue())) {
                    isLoggedIn = true;
                }
                if("userEmail".equals(cookie.getName())){
                    userEmail = cookie.getValue();
                }
            }
            if(isLoggedIn && userEmail != null){
                 Users user = userDao.getUserByEmail(userEmail);
                session.setAttribute("userLogin", AuthenResponse.builder().
                        email(user.getEmail())
                        .userName(user.getUserName())
                        .avatar(user.getAvatar())
                        .userId(user.getId())
                        .wishListQuantity(wishListService.findWishListByUserId(user.getId()).size())
                        .orderQuantity(orderService.findOrderByUserId(user.getId()).size())
                        .cartQuantity(cartService.findAllCartByUserId(user.getId()).size()).
                        build());
                return "redirect:/home";
            }

        }
            model.addAttribute("formLogin", new FormLogin());
            return "/Client/authen/login";

//        return "Admin/authen/login";
    }

    @PostMapping("/login")
    public String doLogin(@ModelAttribute FormLogin formLogin, HttpServletResponse response,
                          Model model,@RequestParam(value = "remember-me", required = false) String rememberMe
                          ) {

        if (authenService.login(formLogin)) {


            Cookie cookie = new Cookie("loginStatus","true");
            cookie.setHttpOnly(true);
            cookie.setPath("/");

            //de tim thong tin user theo session
            Cookie emailUserCookie = new Cookie("userEmail",userDao.getUserByEmail(formLogin.getEmail()).getEmail());
            emailUserCookie.setHttpOnly(true);
            emailUserCookie.setPath("/");


            if(rememberMe != null){
                cookie.setMaxAge(7*24*60*60);
                emailUserCookie.setMaxAge(7*24*60*60);
            }else{
                cookie.setMaxAge(-1);//Nếu user ko chọn remember me thì lưu session cookie(chỉ tồn tại ở phiên làm việc đó)
                emailUserCookie.setMaxAge(-1);
            }
            response.addCookie(cookie);
            response.addCookie(emailUserCookie);

            return "redirect:/home";
        } else {
            model.addAttribute("err", "Sai email hoặc mật khẩu!");
            model.addAttribute("formLogin", formLogin);
            return "/Client/authen/login";
        }
    }
    @GetMapping("/logout")
    public String doLogout(HttpServletResponse response, HttpServletRequest request) {
        AuthenResponse authenResponse = (AuthenResponse) session.getAttribute("userLogin");
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if(cookie.getName().equals(authenResponse.getEmail()))
                cookie.setValue("");
                cookie.setPath("/");
                cookie.setMaxAge(0); // Set expiry to 0 to delete the cookie
                response.addCookie(cookie);
            }
        }

        session.invalidate();

        return "redirect:/";
    }

    @GetMapping("/profile")
    public String viewProfile(Model model){
        AuthenResponse response = (AuthenResponse) session.getAttribute("userLogin");
        Users userLogin = authenService.findById(response.getUserId());
        if (userLogin == null) {
            return "redirect:/auth/login";
        }
        List<Address> addresses = addressService.findAddressByUserId(userLogin.getId());
        model.addAttribute("user", userLogin);

        model.addAttribute("addresses", addresses);

        return "/Client/authen/profile";
    }

    @GetMapping("/update")
    public String updateProfile(Model model) {
        AuthenResponse response = (AuthenResponse) session.getAttribute("userLogin");
        Users userLogin = authenService.findById(response.getUserId());
        if (userLogin == null) {
            return "redirect:/auth/login";
        }
        AuthenEditRequest request = mapper.map(userLogin, AuthenEditRequest.class);
        List<Address> addresses = addressService.findAddressByUserId(userLogin.getId());
        request.setAddresses(addresses);
        model.addAttribute("user", request);
        return "/Client/authen/update";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String updateProfileInfo(@ModelAttribute("user") @Valid AuthenEditRequest user,
                                    BindingResult bindingResult,
                                    Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult);
            AuthenResponse response = (AuthenResponse) session.getAttribute("userLogin");
            Users userLogin = authenService.findById(response.getUserId());
            user.setAvatar(userLogin.getAvatar());
            model.addAttribute("user", user);
            return "/Client/authen/update";
        } else {
            authenService.updateUser(user);
            for (Address address : user.getAddresses()) {
                addressService.updateAddress(address);
            }

            return "redirect:/auth/profile";
        }
    }


}

