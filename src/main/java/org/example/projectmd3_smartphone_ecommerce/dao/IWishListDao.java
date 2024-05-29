package org.example.projectmd3_smartphone_ecommerce.dao;

import org.example.projectmd3_smartphone_ecommerce.entity.Orders;
import org.example.projectmd3_smartphone_ecommerce.entity.WishList;

import java.util.List;

public interface IWishListDao  {
    List<WishList> getAllV2();
    WishList findById(Integer id);

    List<WishList> findByUserId(Integer id);
    boolean addNew(WishList object);
    boolean update(WishList object);
    boolean deleteWishList (Integer wishListId);
}
