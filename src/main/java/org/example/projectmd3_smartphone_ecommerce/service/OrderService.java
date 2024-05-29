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

    public List<Orders> findAllOrder() {
        return orderDao.getAllV2();
    }

    public List<Orders> findOrderByUserId(Integer userId) {
        return this.orderDao.findByUserId(userId);
    }

    public Orders findById(Integer orderId) {

        return this.orderDao.findById(orderId);
    }

    public boolean addNew(Orders orders) {
        mailService.sendMail(EmailManagement.SENDER,
                userService.findByIdV2(orders.getUsers().getId()).getEmail(),
                EmailManagement.REGISTER_SUBJECT,
                EmailManagement.orderConfirmation(
                        userService.findByIdV2(orders.getUsers().getId()).getUserName(),
                        orders.getReceivePhone(),
                        orders.getReceiveName(),
                        orders.getSerialNumber(),
                        orders.getCreatedAt(),
                        orders.getReceiveAddress()
                )
        );
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
