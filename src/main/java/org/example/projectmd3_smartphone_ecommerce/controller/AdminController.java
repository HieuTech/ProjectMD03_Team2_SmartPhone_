package org.example.projectmd3_smartphone_ecommerce.controller;

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

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    ProductServiceImpl productService2;
    @Autowired
    CategoriesService categoriesService;
    @Autowired
    ProductServiceImpl productService;
    @GetMapping("/dashboard")
    public String dashboard(
            @ModelAttribute("product") ProductRequest product,
            Model model,
            @RequestParam(defaultValue = "0") int currentPage,
            @RequestParam(defaultValue = "4") int size,
            @RequestParam(defaultValue = "") String err) {
        model.addAttribute("list", productService2.selectAllProducts(currentPage, size));
        model.addAttribute("totalPages", (int) Math.ceil((double) productService2.countAllProduct() / size));
        model.addAttribute("categories", categoriesService.getAll(0, 100));
        model.addAttribute("err", err);
        return "/Admin/dashboard/dashboard";
    }

    @PostMapping("/addPro")
    public String addPro(@ModelAttribute("product") ProductRequest product, HttpServletRequest request) {
        if (productService2.insertProducts1(product, request)) {
            return "redirect:/auth/dashboard";
        } else {
            return "redirect:/admin/dashboard?err=Please add a category first";
        }
    }



    @PostMapping("/sorf")
    public String search(@ModelAttribute("product") ProductRequest product, Model model,
                         @RequestParam(defaultValue = "0") int currentPage,
                         @RequestParam(defaultValue = "4") int size,
                         @RequestParam(name = "sortBy", required = false) String sortBy) {
        model.addAttribute("list", productService.soft(sortBy,currentPage, size)); // Pass sortBy to the service if needed
        model.addAttribute("totalPages", Math.ceil((double) productService2.countAllProduct() / size));
        model.addAttribute("categories", categoriesService.getAll(0, 100));
        return "/Admin/dashboard/dashboard";
    }


    @GetMapping("/editInit/{id}")
    public String editInit(Model model, @PathVariable int id){
        model.addAttribute("product", productService2.selectProductById(id));
        model.addAttribute("categories", categoriesService.getAll(0,100));
        return "/Admin/dashboard/editProduct";
    }

    @PostMapping("/edit")
    public String edit(Model model, @ModelAttribute("product") @Valid ProductRequest product, BindingResult bindingResult, HttpServletRequest request){
        if(bindingResult.hasErrors()) {
            model.addAttribute("product",product);
            model.addAttribute("categories", categoriesService.getAll(0,100));
            return "/Admin/dashboard/editProduct";
        }
        productService2.updateProduct(product,request);
        return "redirect:/admin/dashboard";
    }

    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable int id){
        if(productService2.deleteProduct(id)){
            return "redirect:/admin/dashboard";
        }else{
            return "redirect:/admin/dashboard?err=The product is in the cart(cannot be deleted)";
        }

    }}
