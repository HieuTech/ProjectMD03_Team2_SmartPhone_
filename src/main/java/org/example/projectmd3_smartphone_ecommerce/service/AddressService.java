package org.example.projectmd3_smartphone_ecommerce.service;


import org.example.projectmd3_smartphone_ecommerce.dao.impl.AddressDaoImpl;
import org.example.projectmd3_smartphone_ecommerce.dto.request.CategoryRequest;
import org.example.projectmd3_smartphone_ecommerce.entity.Address;
import org.example.projectmd3_smartphone_ecommerce.entity.Orders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {
    @Autowired
    private AddressDaoImpl addressDao;

    public List<Address> findAllAddress(){
        return addressDao.getAll();
    }

    public List<Address> findAddressByUserId(Integer userId){
        return this.addressDao.findByUserId(userId);
    }
    public Address findById(Integer addressId){
        return addressDao.findById(addressId);
    }

    public boolean addNew(Address address){
        return this.addressDao.addNew(address);
    }
}