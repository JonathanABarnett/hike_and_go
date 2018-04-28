package com.alaythiaproductions.hike_and_go.service.service;

import com.alaythiaproductions.hike_and_go.model.*;

public interface OrderService {

   Order createOrder(ShoppingCart shoppingCart, ShippingAddress shippingAddress,
                     BillingAddress billingAddress, Payment payment,
                     String shippingMethod, User user);
}
