package com.alaythiaproductions.hike_and_go.service.implementation;

import com.alaythiaproductions.hike_and_go.model.*;
import com.alaythiaproductions.hike_and_go.repository.OrderRepository;
import com.alaythiaproductions.hike_and_go.service.service.CartItemService;
import com.alaythiaproductions.hike_and_go.service.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CartItemService cartItemService;

    @Override
    public synchronized  Order createOrder(ShoppingCart shoppingCart, ShippingAddress shippingAddress, BillingAddress billingAddress, Payment payment, String shippingMethod, User user) {

        Order order = new Order();
        order.setBillingAddress(billingAddress);
        order.setOrderStatus("created");
        order.setPayment(payment);
        order.setShippingAddress(shippingAddress);
        order.setShippingMethod(shippingMethod);

        List<CartItem> cartItemList = cartItemService.findByShoppingCart(shoppingCart);
        for (CartItem cartItem : cartItemList) {
            if (cartItem.getProduct() != null) {
                Product product = cartItem.getProduct();
                cartItem.setOrder(order);
                product.setNumberOfStock(product.getNumberOfStock() - cartItem.getQty());
            }
            if (cartItem.getTravel() != null) {
                Travel travel = cartItem.getTravel();
                cartItem.setOrder(order);
                travel.setMaxPeople(travel.getMaxPeople() - cartItem.getQty());
            }
        }

        order.setCartItemList(cartItemList);
        order.setOrderDate(Calendar.getInstance().getTime());
        order.setOrderTotal(shoppingCart.getGrandTotal());
        shippingAddress.setOrder(order);
        billingAddress.setOrder(order);
        payment.setOrder(order);
        order.setUser(user);
        order = orderRepository.save(order);

        return order;
    }

    public Order findOne(Long id) {
        return orderRepository.findOne(id);
    }

}
