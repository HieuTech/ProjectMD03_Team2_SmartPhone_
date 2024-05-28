package org.example.projectmd3_smartphone_ecommerce.dao;

import org.example.projectmd3_smartphone_ecommerce.entity.Address;
import org.example.projectmd3_smartphone_ecommerce.entity.Orders;

import java.util.List;

public interface IAddressDao {

    List<Address> getAll();
    Address findById(Integer id);

    List<Address> findByUserId(Integer userId);

    boolean addNew(Address address);
    boolean update(Address address);
    boolean delete(Integer addressId);
}
