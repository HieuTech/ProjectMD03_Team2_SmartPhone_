package org.example.projectmd3_smartphone_ecommerce.controller;

import org.example.projectmd3_smartphone_ecommerce.entity.Vouchers;
import org.example.projectmd3_smartphone_ecommerce.service.VoucherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/admin/voucher")
public class VoucherController {
    @Autowired private VoucherService voucherService;

    @GetMapping
    public String showVoucher(Model model, @ModelAttribute("voucher") Vouchers voucher){
        model.addAttribute("voucherlist", voucherService.getAllVouchers());
        return "Admin/dashboard/voucherManager";
    }
    @RequestMapping("/delete")
    public String deleteVoucher(@RequestParam("id") Integer id){
        voucherService.deleteVoucher(id);
        return "redirect:/admin/voucher";
    }
}
