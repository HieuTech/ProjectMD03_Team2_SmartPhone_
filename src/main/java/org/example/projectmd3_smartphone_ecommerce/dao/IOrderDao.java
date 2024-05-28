package org.example.projectmd3_smartphone_ecommerce.dao;

import org.example.projectmd3_smartphone_ecommerce.dto.request.OrdersRequest;
import org.example.projectmd3_smartphone_ecommerce.entity.Orders;

import java.util.List;

public interface IOrderDao extends IGenericDao<Orders, OrdersRequest, Integer> {

    List<Orders> getAllV2();
    Orders findById(Integer id);

    List<Orders> findByUserId(Integer id);

    boolean addNew(Orders object);
    boolean update(Orders object);
}
