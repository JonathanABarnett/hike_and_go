package com.alaythiaproductions.hike_and_go.service.implementation;

import com.alaythiaproductions.hike_and_go.model.*;
import com.alaythiaproductions.hike_and_go.repository.CartItemRepository;
import com.alaythiaproductions.hike_and_go.repository.ProductToCartItemRepository;
import com.alaythiaproductions.hike_and_go.repository.TravelToCartItemRepository;
import com.alaythiaproductions.hike_and_go.service.service.CartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class CartItemServiceImpl implements CartItemService {

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private ProductToCartItemRepository productToCartItemRepository;

    @Autowired
    private TravelToCartItemRepository travelToCartItemRepository;


    @Override
    public List<CartItem> findByShoppingCart(ShoppingCart shoppingCart) {
        return cartItemRepository.findByShoppingCart(shoppingCart);
    }

    @Override
    public CartItem updateCartItem(CartItem cartItem) {
        BigDecimal subTotal = new BigDecimal(0);

        if (cartItem.getProduct() != null) {
            subTotal = new BigDecimal(cartItem.getProduct().getOurPrice()).multiply(new BigDecimal(cartItem.getQty()));
            subTotal = subTotal.setScale(2, BigDecimal.ROUND_HALF_UP);
        }
        if (cartItem.getTravel() != null) {
            subTotal = new BigDecimal(cartItem.getTravel().getOurPrice()).multiply(new BigDecimal(cartItem.getQty()));
            subTotal = subTotal.setScale(2, BigDecimal.ROUND_HALF_UP);
        }
        cartItem.setSubTotal(subTotal);

        return cartItemRepository.save(cartItem);
    }

    @Override
    public CartItem addProductToCartItem(Product product, User user, Integer quantity) {
        List<CartItem> cartItemList = findByShoppingCart(user.getShoppingCart());

        for (CartItem cartItem : cartItemList) {
            if (cartItem.getProduct() != null) {
                if (product.getId() == cartItem.getProduct().getId()) {
                    cartItem.setQty(cartItem.getQty() + quantity);
                    cartItem.setSubTotal(new BigDecimal(product.getOurPrice()).multiply(new BigDecimal(quantity)));
                    cartItemRepository.save(cartItem);
                    return cartItem;
                }
            }
        }

        CartItem cartItem = new CartItem();
        cartItem.setShoppingCart(user.getShoppingCart());
        cartItem.setProduct(product);

        cartItem.setQty(quantity);
        cartItem.setSubTotal(new BigDecimal(product.getOurPrice()).multiply(new BigDecimal(quantity)));
        cartItemRepository.save(cartItem);

        ProductToCartItem productToCartItem = new ProductToCartItem();
        productToCartItem.setProduct(product);
        productToCartItem.setCartItem(cartItem);
        productToCartItemRepository.save(productToCartItem);

        return cartItem;
    }

    @Override
    public CartItem addTravelToCartItem(Travel travel, User user, Integer quantity) {
        List<CartItem> cartItemList = findByShoppingCart(user.getShoppingCart());

        for (CartItem cartItem : cartItemList) {
            if (cartItem.getTravel() != null) {
                if (travel.getId() == cartItem.getTravel().getId()) {
                    cartItem.setQty(cartItem.getQty() + quantity);
                    cartItem.setSubTotal(new BigDecimal(travel.getOurPrice()).multiply(new BigDecimal(quantity)));
                    cartItemRepository.save(cartItem);
                    return cartItem;
                }
            }
        }

        CartItem cartItem = new CartItem();
        cartItem.setShoppingCart(user.getShoppingCart());
        cartItem.setTravel(travel);

        cartItem.setQty(quantity);
        cartItem.setSubTotal(new BigDecimal(travel.getOurPrice()).multiply(new BigDecimal(quantity)));
        cartItemRepository.save(cartItem);

        TravelToCartItem travelToCartItem = new TravelToCartItem();
        travelToCartItem.setTravel(travel);
        travelToCartItem.setCartItem(cartItem);
        travelToCartItemRepository.save(travelToCartItem);

        return cartItem;
    }

    @Override
    public CartItem findById(Long cartItemId) {
        return cartItemRepository.findOne(cartItemId);
    }

    @Override
    public void removeCartItem(CartItem cartItem) {
        if (cartItem.getProduct() != null) {
            productToCartItemRepository.deleteByCartItem(cartItem);
        }
        if (cartItem.getProduct() != null) {
            travelToCartItemRepository.deleteByCartItem(cartItem);
        }
        cartItemRepository.delete(cartItem);
    }

    public CartItem save(CartItem cartItem) {
        return cartItemRepository.save(cartItem);
    }

    @Override
    public List<CartItem> findByOrder(Order order) {
        return cartItemRepository.findByOrder(order);
    }
}
