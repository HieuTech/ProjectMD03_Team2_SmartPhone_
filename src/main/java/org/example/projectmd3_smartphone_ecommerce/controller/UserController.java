package org.example.projectmd3_smartphone_ecommerce.controller;


import org.example.projectmd3_smartphone_ecommerce.dao.impl.UserDaoImpl;
import org.example.projectmd3_smartphone_ecommerce.entity.UserRoles;
import org.example.projectmd3_smartphone_ecommerce.entity.Users;
import org.example.projectmd3_smartphone_ecommerce.service.AuthenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
public class UserController {
    @Autowired
    private AuthenService authenService;
    @GetMapping("/management")
    public String showUserList(Model model) {
        model.addAttribute("userList", authenService.getAll());
        return "Admin/management/user";
    }

    //  Xem thông tin chi tiết
    @GetMapping("/management/view/{id}")
    public String viewUser(Model model, @PathVariable int id) {
        Users userLogin = authenService.findById(id);
        model.addAttribute("user", userLogin);
        return "Admin/management/view-user";
    }


    @GetMapping("/management/block/{id}")
    public String blockUser(@PathVariable int id) {
        Users user = authenService.findById(id);
        if (user != null) {
            authenService.block(user); // Save the changes to the database
        }
        return "redirect:/users/management"; // Redirect to the management page
    }

}
