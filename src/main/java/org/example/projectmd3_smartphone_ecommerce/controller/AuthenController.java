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

}

