package org.example.projectmd3_smartphone_ecommerce.dao;

import org.example.projectmd3_smartphone_ecommerce.entity.Vouchers;
import org.example.projectmd3_smartphone_ecommerce.entity.WishList;

import java.util.List;

public interface IVoucherDao {
    List<Vouchers> getAllV2();
    Vouchers findById(Integer id);

    List<Vouchers> findByUserId(Integer id);
    boolean addNew(Vouchers object);
    boolean update(Vouchers object);
    boolean deleteVouchers (Integer wishListId);
}
