package org.example.projectmd3_smartphone_ecommerce.controller;


import org.example.projectmd3_smartphone_ecommerce.dto.response.CartResponse;
import org.example.projectmd3_smartphone_ecommerce.entity.Address;
import org.example.projectmd3_smartphone_ecommerce.entity.EnumOrders;
import org.example.projectmd3_smartphone_ecommerce.entity.Orders;
import org.example.projectmd3_smartphone_ecommerce.service.AddressService;
import org.example.projectmd3_smartphone_ecommerce.service.CartService;
import org.example.projectmd3_smartphone_ecommerce.service.OrderService;
import org.example.projectmd3_smartphone_ecommerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.UUID;

@Controller
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private AddressService addressService;
    @Autowired
    private UserService userService;
    @Autowired
    private CartService cartService;



    @GetMapping()
    public String orders(Model model) {
        model.addAttribute("orders", new Orders());
        model.addAttribute("addressList", addressService.findAddressByUserId(1));
        double totalPrice = 0;
        for (CartResponse cartResponse : this.cartService.findAllCartByUserId(1)){
            totalPrice += cartResponse.getProductPrice();
            model.addAttribute("totalPrice", totalPrice);
        }
        return "Client/orders/checkout";
    }

    @PostMapping("/checkout")
    public String checkout(@ModelAttribute() Orders orders ) {
        orders.setCreatedAt(new Date());
        orders.setSerialNumber(UUID.randomUUID().toString());
        orders.setUsers(userService.findByIdV2(1));
        orders.setStatus("Waiting");
        orderService.addNew(orders);
        return "redirect:/orders/success";
    }

    @GetMapping("/success")
    public String success() {

        return "Client/orders/success";
    }


}
