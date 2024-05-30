package org.example.projectmd3_smartphone_ecommerce.controller;

import org.example.projectmd3_smartphone_ecommerce.dto.request.CategoryRequest;

import org.example.projectmd3_smartphone_ecommerce.service.CategoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequestMapping("admin/categories")
@Controller
public class CategoriesController {
    @Autowired private CategoriesService categoriesService;
    @GetMapping
    public String categories(CategoryRequest categories, Model model, @RequestParam(defaultValue = "0") int currentPage, @RequestParam(defaultValue = "5") int size) {
        model.addAttribute("categoriesList", categoriesService.getAll(currentPage, size));
        model.addAttribute("totalPages",Math.ceil( (double) categoriesService.countAllCategories() / size));
        model.addAttribute("category",  categories);
        return "Admin/dashboard/categories";
    }
    @PostMapping("/add")
    public String addCategory(CategoryRequest category) {
        categoriesService.addNew(category);
        return "redirect:/categories";
    }
    @GetMapping("/editInit/{id}")
    public String editInit(Model model, @PathVariable int id) {
        model.addAttribute("category", categoriesService.findById(id));
        return "Admin/dashboard/editCategory";
    }
    @PostMapping("/edit")
    public String editCategory(CategoryRequest category) {
        categoriesService.update(category, category.getId());
        return "redirect:/categories";
    }

    @RequestMapping("/delete/{id}")
    public String deleteCategory( @PathVariable int id) {
        categoriesService.delete(id);
        return "redirect:/categories";
    }
}
