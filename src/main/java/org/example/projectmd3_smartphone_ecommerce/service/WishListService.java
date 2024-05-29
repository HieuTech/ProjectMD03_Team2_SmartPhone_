package org.example.projectmd3_smartphone_ecommerce.service;

import org.example.projectmd3_smartphone_ecommerce.dao.impl.WishListDaoImpl;
import org.example.projectmd3_smartphone_ecommerce.dto.response.WishListResponse;
import org.example.projectmd3_smartphone_ecommerce.entity.Orders;
import org.example.projectmd3_smartphone_ecommerce.entity.WishList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WishListService {
    @Autowired
    private WishListDaoImpl wishListDao;
    @Autowired
    private ProductService productService;


    public List<WishList> findAllWishList() {
        return wishListDao.getAllV2();
    }

    public List<WishListResponse> findWishListByUserId(Integer userId) {
        List<WishListResponse> listResponses = new ArrayList<>();
        for (WishList wishList : this.wishListDao.findByUserId(userId)) {
           listResponses.add( WishListResponse.builder().
                   wishListId(wishList.getId()).
                   productId(productService.findById(wishList.getProducts().getId()).getId()).
                   productImage(productService.findById(wishList.getProducts().getId()).getImage())
                   .productName(productService.findById(wishList.getProducts().getId()).getName()).
                   productPrice(productService.findById(wishList.getProducts().getId()).getUnitPrice()).
                   build());
        }

        return listResponses;
    }

    public WishList findById(Integer orderId) {

        return this.wishListDao.findById(orderId);
    }

    public boolean addNew(WishList orders) {
        return this.wishListDao.addNew(orders);
    }

    public boolean deleteWishList(Integer wishListId) {
        return wishListDao.deleteWishList(wishListId);
    }

}
