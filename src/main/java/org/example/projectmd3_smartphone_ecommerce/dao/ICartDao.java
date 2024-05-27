package org.example.projectmd3_smartphone_ecommerce.dao;

import org.example.projectmd3_smartphone_ecommerce.dto.request.CartRequest;
import org.example.projectmd3_smartphone_ecommerce.dto.response.CartResponse;
import org.example.projectmd3_smartphone_ecommerce.entity.ShoppingCarts;

import java.util.List;

public interface ICartDao {

    List<ShoppingCarts> showAllCartByUserId(Integer userId);
    boolean updateCart(CartRequest req);

    ShoppingCarts findByCartId(Integer cartId);

    boolean addToCart(CartRequest req);
}
