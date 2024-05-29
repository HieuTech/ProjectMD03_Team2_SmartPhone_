package org.example.projectmd3_smartphone_ecommerce.controller;

import org.example.projectmd3_smartphone_ecommerce.entity.Address;
import org.example.projectmd3_smartphone_ecommerce.entity.Users;
import org.example.projectmd3_smartphone_ecommerce.service.AddressService;
import org.example.projectmd3_smartphone_ecommerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/address")
public class AddressController {
    @Autowired
    private AddressService addressService;
    @Autowired
    private UserService userService;

    @Autowired
    private HttpSession session;
    @GetMapping
    public String getFormAddress(Model model){
        model.addAttribute("address", new Address());
        return "Client/address/addAddress";
    }

    @PostMapping()
    public String addAddress(@ModelAttribute("address") Address address, Model model){

        address.setUsers(userService.findByIdV2(1));
        this.addressService.addNew(address);

        return "redirect:/orders";
    }
}
