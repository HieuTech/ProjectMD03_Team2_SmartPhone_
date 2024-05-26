package org.example.projectmd3_smartphone_ecommerce.dao.impl;

import org.example.projectmd3_smartphone_ecommerce.dao.ICartDao;
import org.example.projectmd3_smartphone_ecommerce.dto.request.CartRequest;
import org.example.projectmd3_smartphone_ecommerce.entity.ShoppingCarts;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CartDaoImpl implements ICartDao {
    @Override
    public List<ShoppingCarts> showAllCartByUserId(Integer userId) {
        return null;
    }

    @Override
    public boolean updateCart(CartRequest req) {
        return false;
    }

    @Override
    public boolean addToCart(CartRequest req) {
        return false;
    }
}
