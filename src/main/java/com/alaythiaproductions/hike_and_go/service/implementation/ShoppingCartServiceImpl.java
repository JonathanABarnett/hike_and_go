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


    @Override
    public ShoppingCart updateShoppingCart(ShoppingCart shoppingCart) {
        BigDecimal cartTotal = new BigDecimal(0);

        List<CartItem> cartItemList = cartItemService.findByShoppingCart(shoppingCart);

        for (CartItem cartItem : cartItemList) {

            if (cartItem.getProduct() != null) {
                if (cartItem.getProduct().getNumberOfStock() > 0) {
                    cartItemService.updateCartItem(cartItem);
                    cartTotal = cartTotal.add(cartItem.getSubTotal());
                }
            }
            if (cartItem.getTravel() != null) {
                if (cartItem.getTravel().getMaxPeople() > 0) {
                    cartItemService.updateCartItem(cartItem);
                    cartTotal = cartTotal.add(cartItem.getSubTotal());
                }
            }
        }

        shoppingCart.setGrandTotal(cartTotal);

        shoppingCartRepository.save(shoppingCart);

        return shoppingCart;
    }

    @Override
    public void clearShoppingCart(ShoppingCart shoppingCart) {
        List<CartItem> cartItemList = cartItemService.findByShoppingCart(shoppingCart);

        for (CartItem cartItem : cartItemList) {
            cartItem.setShoppingCart(null);
            cartItemService.save(cartItem);
        }

        shoppingCart.setGrandTotal(new BigDecimal(0));

        shoppingCartRepository.save(shoppingCart);
    }
}
