package com.alaythiaproductions.hike_and_go.service.implementation;

import com.alaythiaproductions.hike_and_go.model.CartItem;
import com.alaythiaproductions.hike_and_go.model.ShoppingCart;
import com.alaythiaproductions.hike_and_go.repository.ShoppingCartRepository;
import com.alaythiaproductions.hike_and_go.service.service.CartItemService;
import com.alaythiaproductions.hike_and_go.service.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    @Autowired
    private CartItemService cartItemService;

    @Autowired
    private ShoppingCartService shoppingCartService;

    @Override
    public ShoppingCart updateShoppingCart(ShoppingCart shoppingCart) {
        BigDecimal cartTotal = new BigDecimal(0);

        List<CartItem> cartItemList = cartItemService.findByShoppingCart(shoppingCart);

        for (CartItem cartItem : cartItemList) {
            if(cartItem.getProduct().getNumberOfStock() > 0) {
                cartItemService.updateCartItem(cartItem);
                cartTotal = cartTotal.add(cartItem.getSubTotal());
            }
        }

        shoppingCart.setGrandTotal(cartTotal);

        shoppingCartRepository.save(shoppingCart);

        return shoppingCart;
    }
}
