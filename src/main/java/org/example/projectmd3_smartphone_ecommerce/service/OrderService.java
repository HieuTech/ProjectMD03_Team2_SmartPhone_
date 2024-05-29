package org.example.projectmd3_smartphone_ecommerce.service;


import org.example.projectmd3_smartphone_ecommerce.dao.impl.CartDaoImpl;
import org.example.projectmd3_smartphone_ecommerce.dao.impl.OrderDaoImpl;
import org.example.projectmd3_smartphone_ecommerce.entity.Orders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderDaoImpl orderDao;
    @Autowired
    private CartDaoImpl cartDao;

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
        cartDao.deleteCartByUserId(orders.getUsers().getId());
        return this.orderDao.addNew(orders);
    }
}
