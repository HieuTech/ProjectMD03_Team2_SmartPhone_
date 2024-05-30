package org.example.projectmd3_smartphone_ecommerce.service;


import org.example.projectmd3_smartphone_ecommerce.dao.impl.AddressDaoImpl;
import org.example.projectmd3_smartphone_ecommerce.dto.response.AuthenResponse;
import org.example.projectmd3_smartphone_ecommerce.entity.Address;
import org.example.projectmd3_smartphone_ecommerce.entity.Users;
import org.example.projectmd3_smartphone_ecommerce.service.impl.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;

@Service
public class AddressService implements IAddressService {
    @Autowired
    private AddressDaoImpl addressDao;
    @Autowired
    private HttpSession session;
    @Autowired
    private AuthenService authenService;

    @Override
    public List<Address> findAllAddress() {
        return addressDao.getAll();
    }

    @Override

    public List<Address> findAddressByUserId(Integer userId) {
        return this.addressDao.findByUserId(userId);
    }

    @Override

    public Address findById(Integer addressId) {
        return addressDao.findById(addressId);
    }

    @Override

    public boolean addNew(Address address) {
        return this.addressDao.addNew(address);
    }

    @Override

    public boolean updateAddress(Address address) {
        AuthenResponse response = (AuthenResponse) session.getAttribute("userLogin");
        Users userLogin = authenService.findById(response.getUserId());
        address.setUsers(userLogin);
        return addressDao.update(address);
    }

    ;
}

