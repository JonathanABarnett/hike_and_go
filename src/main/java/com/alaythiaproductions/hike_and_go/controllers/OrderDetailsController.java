package com.alaythiaproductions.hike_and_go.controllers;

import com.alaythiaproductions.hike_and_go.model.CartItem;
import com.alaythiaproductions.hike_and_go.model.Order;
import com.alaythiaproductions.hike_and_go.model.User;
import com.alaythiaproductions.hike_and_go.model.UserShipping;
import com.alaythiaproductions.hike_and_go.service.service.CartItemService;
import com.alaythiaproductions.hike_and_go.service.service.OrderService;
import com.alaythiaproductions.hike_and_go.service.service.UserService;
import com.alaythiaproductions.hike_and_go.utility.USConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.Collections;
import java.util.List;

@Controller
public class OrderDetailsController {

    @Autowired
    private UserService userService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private CartItemService cartItemService;

    @RequestMapping(value = "/orderDetail")
    public String orderDetail(@RequestParam("id") long orderId, Principal principal, Model model) {
        User user = userService.findByUsername(principal.getName());
        Order order = orderService.findOne(orderId);

        if (order.getUser().getId() != user.getId()) {
            return "badRequestPage";
        } else {
            List<CartItem> cartItemList = cartItemService.findByOrder(order);
            model.addAttribute("cartItemList", cartItemList);
            model.addAttribute("user", user);
            model.addAttribute("order", order);

            model.addAttribute("userPaymentList", user.getUserPaymentList());
            model.addAttribute("userShippingList", user.getUserShippingList());
            model.addAttribute("orderList", user.getOrderList());

            UserShipping userShipping = new UserShipping();

            model.addAttribute("userShipping", userShipping);

            List<String> stateList = USConstants.listOfUSStateCode;
            Collections.sort(stateList);
            model.addAttribute("stateList", stateList);

            model.addAttribute("listOfShippingAddresses", true);
            model.addAttribute("classActiveSupplyOrders", true);
            model.addAttribute("displayOrderDetail", true);
            model.addAttribute("listOfCreditCards", true);

            return "myProfile";
        }
    }


}
