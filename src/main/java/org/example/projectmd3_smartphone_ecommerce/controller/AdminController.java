package org.example.projectmd3_smartphone_ecommerce.controller;

import org.example.projectmd3_smartphone_ecommerce.entity.Users;
import org.example.projectmd3_smartphone_ecommerce.service.AuthenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
}
