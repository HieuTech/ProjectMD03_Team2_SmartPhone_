package org.example.projectmd3_smartphone_ecommerce.service;

import org.example.projectmd3_smartphone_ecommerce.dao.impl.CartDaoImpl;
import org.example.projectmd3_smartphone_ecommerce.dao.impl.UserDaoImpl;
import org.example.projectmd3_smartphone_ecommerce.dto.request.CartRequest;
import org.example.projectmd3_smartphone_ecommerce.dto.response.CartResponse;
import org.example.projectmd3_smartphone_ecommerce.entity.ShoppingCarts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartService {
    @Autowired
    private CartDaoImpl cartDao;
    @Autowired
    private UserDaoImpl userDao;

    public List<CartResponse> findAllCartByUserId(Integer userId){
        List<CartResponse> list = new ArrayList<>();

        for (ShoppingCarts cart: cartDao.showAllCartByUserId(userId)){
            list.add(CartResponse.builder()
                            .id(cart.getId())
                    .productId(cart.getProducts().getId())
                    .productName(cart.getProducts().getName())
                    .productImg(cart.getProducts().getImage())
                    .productPrice(cart.getProducts().getUnitPrice())
                    .quantity(cart.getOrderQuantity())
                    .build());
        }
        return list;
    }

    public CartResponse findCartById(Integer cartId){
        ShoppingCarts carts = cartDao.findByCartId(cartId);
        if(carts != null){
            return CartResponse.builder()
                    .id(carts.getId())
                    .quantity(carts.getOrderQuantity())
                    .productId(carts.getProducts().getId())
                    .productImg(carts.getProducts().getImage())
                    .productName(carts.getProducts().getName())
                    .productPrice(carts.getProducts().getUnitPrice())
                    .build();
        }else{
            return null;
        }
    }

    public boolean updateCart(CartRequest request){
         return this.cartDao.updateCart(request);
    }

    public boolean addToCart(CartRequest request){
        this.cartDao.addToCart(request);
        return true;
    }

    public boolean deleteCart(Integer cartId){
        return cartDao.deleteCart(cartId);
    }

    public boolean deleteCartByUserId(Integer userId){
        return cartDao.deleteCartByUserId(userId);
    }
}
