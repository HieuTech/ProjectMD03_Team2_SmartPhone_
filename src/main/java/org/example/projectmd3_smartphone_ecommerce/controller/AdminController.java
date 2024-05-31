package org.example.projectmd3_smartphone_ecommerce.controller;

import org.example.projectmd3_smartphone_ecommerce.entity.Products;
import org.example.projectmd3_smartphone_ecommerce.entity.Users;
import org.example.projectmd3_smartphone_ecommerce.service.AuthenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.example.projectmd3_smartphone_ecommerce.dto.request.ProductRequest;
import org.example.projectmd3_smartphone_ecommerce.service.CategoriesService;
import org.example.projectmd3_smartphone_ecommerce.service.impl.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import java.util.List;

@Controller
@RequestMapping(value = {"/admin"})
public class AdminController {
    @Autowired
    private AuthenService authenService;

    @GetMapping("/user")
    public String showUserList(Model model,
                               @RequestParam(defaultValue = "1") int page,
                               @RequestParam(defaultValue = "1") int pageSize,
                               @RequestParam(required = false) String keyword,
                               @RequestParam(required = false) String sortBy,
                               @RequestParam(required = false) String sortOrder) {

        List<Users> userList = authenService.getUserList(page, pageSize, keyword, sortBy, sortOrder);
        Integer totalPages = authenService.getTotalPages(pageSize, keyword);
        model.addAttribute("userList", userList);
        model.addAttribute("currentPage", page);
        model.addAttribute("pageSize", pageSize);
        model.addAttribute("keyword", keyword);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("sortBy", sortBy);
        model.addAttribute("sortOrder", sortOrder);

        return "Admin/management/user";
    }

    //  Xem thông tin chi tiết
    @GetMapping("/user/view/{id}")
    public String viewUser(Model model, @PathVariable int id) {
        Users userLogin = authenService.findById(id);
        model.addAttribute("user", userLogin);
        return "Admin/management/view-user";
    }


    @GetMapping("/user/block/{id}")
    public String blockUser(@PathVariable int id) {
        Users user = authenService.findById(id);
        if (user != null) {
            authenService.block(user); // Save the changes to the database
        }
        return "redirect:/users/management"; // Redirect to the management page
//            authenService.block(user);
//        }
//        return "redirect:/users/management";
    }

    @Autowired
    ProductServiceImpl productService2;
    @Autowired
    CategoriesService categoriesService;
    @Autowired
    ProductServiceImpl productService;

//    public String dashboard(
//            @ModelAttribute("product") ProductRequest product,
//            Model model,
//            @RequestParam(defaultValue = "0") int currentPage,
//            @RequestParam(defaultValue = "4") int size,
//            @RequestParam(defaultValue = "") String err) {
//        model.addAttribute("list", productService2.selectAllProducts(currentPage, size));
//        model.addAttribute("totalPages", (int) Math.ceil((double) productService2.countAllProduct() / size));
//        model.addAttribute("categories", categoriesService.getAll(0, 100));
//        model.addAttribute("err", err);
//        return "/Admin/dashboard/dashboard";
//    }

    @PostMapping("/addPro")
    public String addPro(@ModelAttribute("product") @Valid ProductRequest product, BindingResult bindingResult, HttpServletRequest request, Model model,
                         @RequestParam(defaultValue = "0") int currentPage,
                         @RequestParam(defaultValue = "4") int size,
                         @RequestParam(name = "sortBy", defaultValue = "none") String sortBy) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("sortBy", sortBy);
            model.addAttribute("list", productService.soft(sortBy, currentPage, size));
            model.addAttribute("totalPages", Math.ceil((double) productService2.countAllProduct() / size));
            model.addAttribute("categories", categoriesService.getAll(0, 100));
            model.addAttribute("err", "Could you please fill in all the blanks?");
            return "/Admin/dashboard/dashboard";
        }
        if (productService2.insertProducts1(product)) {
            return "redirect:/admin/dashboard";
        } else {
            return "redirect:/admin/dashboard?err=Please add a category first";
        }
    }

    @RequestMapping("/dashboard")
    public String search(@ModelAttribute("product") Products product, Model model,
                         @RequestParam(defaultValue = "0") int currentPage,
                         @RequestParam(defaultValue = "4") int size,
                         @RequestParam(name = "sortBy", defaultValue = "none") String sortBy) {
        model.addAttribute("sortBy", sortBy);
        model.addAttribute("list", productService.soft(sortBy, currentPage, size));
        model.addAttribute("totalPages", Math.ceil((double) productService2.countAllProduct() / size));
        model.addAttribute("categories", categoriesService.getAll(0, 100));
        return "/Admin/dashboard/dashboard";
    }



    @GetMapping("/editInit/{id}")
    public String editInit(Model model, @PathVariable int id) {
        model.addAttribute("product", productService2.selectProductById(id));
        model.addAttribute("categories", categoriesService.getAll(0, 100));
        return "/Admin/dashboard/editProduct";
    }

    @PostMapping("/edit")
    public String edit(Model model, @ModelAttribute("product") @Valid ProductRequest product, BindingResult bindingResult, HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("product", product);
            model.addAttribute("categories", categoriesService.getAll(0, 100));
            return "/Admin/dashboard/editProduct";
        }
        productService2.updateProduct(product);
        return "redirect:/admin/dashboard";
    }

    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable int id) {
        if (productService2.deleteProduct(id)) {
            return "redirect:/admin/dashboard";
        } else {
            return "redirect:/admin/dashboard?err=The product is in the cart(cannot be deleted)";
        }
    }
}