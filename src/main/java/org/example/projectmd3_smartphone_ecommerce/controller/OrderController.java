package org.example.projectmd3_smartphone_ecommerce.controller;


import org.example.projectmd3_smartphone_ecommerce.dto.response.AuthenResponse;
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

import javax.servlet.http.HttpSession;
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


    @Autowired
    HttpSession session;

    @GetMapping("/clients")
    public String orderClients(Model model) {
        AuthenResponse authenResponse = (AuthenResponse) session.getAttribute("userLogin");
        model.addAttribute("orderList", orderService.findOrderByUserId(authenResponse.getUserId()));
        double totalPrice = 0;
        for (CartResponse cartResponse : this.cartService.findAllCartByUserId(authenResponse.getUserId())){
            totalPrice += cartResponse.getProductPrice();
            model.addAttribute("totalPrice", totalPrice);
        }
        return "Client/orders/clientOrders";
    }
    @GetMapping("/clients/viewDetail/{orderId}")
    public String orderClientsViewDetail(@PathVariable("orderId") Integer orderId, Model model) {
        model.addAttribute("order", orderService.findById(orderId));
        return "Client/orders/orderViewDetail";
    }
    @GetMapping("/clients/update/{orderId}")
    public String orderClientsUpdate(@PathVariable("orderId") Integer orderId, Model model) {
        model.addAttribute("orders", orderService.findById(orderId));
        return "Client/orders/updateOrder";
    }
    @PostMapping("/clients/update")
    public String doOrderClientsUpdate(@ModelAttribute("orders") Orders orders, Model model) {

//        model.addAttribute("orders", orderService.findById(orderId));
        return "Client/orders/updateOrder";
    }


    @GetMapping()
    public String orders(Model model) {
        AuthenResponse authenResponse = (AuthenResponse) session.getAttribute("userLogin");
        model.addAttribute("orders", new Orders());
        model.addAttribute("addressList", addressService.findAddressByUserId(1));
        double totalPrice = 0;
        for (CartResponse cartResponse : this.cartService.findAllCartByUserId(authenResponse.getUserId())){
            totalPrice += cartResponse.getProductPrice();
            model.addAttribute("totalPrice", totalPrice);
        }
        return "Client/orders/checkout";
    }
    @RequestMapping("/management")
    public String ordersManagement(Model model,  @RequestParam(defaultValue = "0") int currentPage,
                                   @RequestParam(defaultValue = "4") int size,
                                   @RequestParam(name = "sortBy", defaultValue = "none") String sortBy) {
        model.addAttribute("sortBy", sortBy);
        model.addAttribute("totalPages", Math.ceil((double) orderService.findAllOrder().size() / size));
        model.addAttribute("orderList",this.orderService.sortORDER(currentPage, size, sortBy));
        return "Admin/orders/ordersManagement";
    }
    @PostMapping("/updateStatus/{orderId}")
    public String updateOrderStatus(@PathVariable("orderId") Integer orderId, @RequestParam("statusValue") String statusValue, Model model) {
        Orders orders = orderService.findById(orderId);
        orders.setStatus(statusValue);
        orderService.updateStatus(orders);
        System.out.println("statusValue" + statusValue);
        return "redirect:/orders/management";
    }



    @PostMapping("/checkout")
    public String checkout(@ModelAttribute() Orders orders, @RequestParam(value = "useVoucher", required = false) String useVoucher ) {
        AuthenResponse authenResponse = (AuthenResponse) session.getAttribute("userLogin");
        double totalPrice = 0;
        for (CartResponse cartResponse : this.cartService.findAllCartByUserId(authenResponse.getUserId())){
            totalPrice += cartResponse.getProductPrice();
        }
        // If voucher is used, apply discount
        if ("true".equals(useVoucher)) {
            // Assuming a fixed discount percentage for simplicity
            double discountPercentage = 5.0; // 5% discount
            totalPrice = totalPrice * (1 - (discountPercentage / 100));
        }
        orders.setTotalPrice(totalPrice);
        orders.setCreatedAt(new Date());
        orders.setSerialNumber(UUID.randomUUID().toString());
        orders.setUsers(userService.findByIdV2(authenResponse.getUserId()));
        orders.setStatus("Waiting");
        orderService.addNew(orders);
        return "redirect:/orders/success";
    }



    @GetMapping("/success")
    public String success() {
        return "Client/orders/success";
    }


}
