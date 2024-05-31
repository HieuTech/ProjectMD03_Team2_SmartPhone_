package org.example.projectmd3_smartphone_ecommerce.service;


import org.example.projectmd3_smartphone_ecommerce.config.EmailManagement;
import org.example.projectmd3_smartphone_ecommerce.dao.impl.CartDaoImpl;
import org.example.projectmd3_smartphone_ecommerce.dao.impl.OrderDaoImpl;
import org.example.projectmd3_smartphone_ecommerce.entity.Orders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class OrderService {
    @Autowired
    private OrderDaoImpl orderDao;
    @Autowired
    private CartDaoImpl cartDao;
    @Autowired
    private MailService mailService;
    @Autowired
    private UserService userService;
    @Autowired
    private VoucherService voucherService;

    public List<Orders> sortORDER(Integer currenPage, Integer pageSize, String sortBy) {
       return orderDao.sortORDER(sortBy,currenPage,pageSize);
    }

    public List<Orders> findAllOrder() {
        return orderDao.getAllV2();
    }

    public List<Orders> findOrderByUserId(Integer userId) {
        return this.orderDao.findByUserId(userId);
    }

    public Orders findById(Integer orderId) {
        return this.orderDao.findById(orderId);
    }

    public boolean addNew(Orders orders, Boolean isUsedVoucher, String voucherCode) {
        mailService.sendMail(EmailManagement.SENDER,
                userService.findByIdV2(orders.getUsers().getId()).getEmail(),
                EmailManagement.ORDER_SUBJECT,
                EmailManagement.orderConfirmation(
                        isUsedVoucher ? voucherCode : "Do Not Apply",
                        userService.findByIdV2(orders.getUsers().getId()).getUserName(),
                        orders.getReceivePhone(),
                        orders.getReceiveName(),
                        orders.getSerialNumber(),
                        orders.getCreatedAt(),
                        orders.getReceiveAddress()
                )
        );
        if(isUsedVoucher){
            voucherService.deleteVoucherByVoucherCode(voucherCode);
        }
        cartDao.deleteCartByUserId(orders.getUsers().getId());
        return this.orderDao.addNew(orders);
    }

    public boolean updateStatus(Orders orders){
        String trackingNumber = UUID.randomUUID().toString();

        mailService.sendMail(EmailManagement.SENDER,
                userService.findByIdV2(orders.getUsers().getId()).getEmail(),
                EmailManagement.CONFIRM_ORDER_SUBJECT,
                EmailManagement.orderShipmentConfirmation(
                        userService.findByIdV2(orders.getUsers().getId()).getUserName(),
                        orders.getSerialNumber(),
                        trackingNumber,
                        "4 days",
                        "https://SmartPhone.com"
                )
        );

        return this.orderDao.update(orders);
    }
}
