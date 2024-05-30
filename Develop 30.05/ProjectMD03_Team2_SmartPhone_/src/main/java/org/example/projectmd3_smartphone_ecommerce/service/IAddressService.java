package org.example.projectmd3_smartphone_ecommerce.service;

import org.example.projectmd3_smartphone_ecommerce.entity.Address;

import java.util.List;

public interface IAddressService {
    List<Address> findAllAddress();

    List<Address> findAddressByUserId(Integer userId);

    Address findById(Integer addressId);

    boolean addNew(Address address) ;

     boolean updateAddress(Address address);
}
