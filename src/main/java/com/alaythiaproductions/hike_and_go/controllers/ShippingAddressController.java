package com.alaythiaproductions.hike_and_go.controllers;

import com.alaythiaproductions.hike_and_go.model.User;
import com.alaythiaproductions.hike_and_go.model.UserShipping;
import com.alaythiaproductions.hike_and_go.service.service.UserService;
import com.alaythiaproductions.hike_and_go.utility.USConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.Collections;
import java.util.List;

@Controller
public class ShippingAddressController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/listOfShippingAddresses")
    public String listOfShippingAddresses(Model model, Principal principal) {
        model.addAttribute("title", "My Profile");

        User user = userService.findByUsername(principal.getName());
        model.addAttribute("user", user);
        model.addAttribute("userPaymentList", user.getUserPaymentList());
        model.addAttribute("userShippingList", user.getUserShippingList());
//        model.addAttribute("orderList", user.getOrderList());
//        model.addAttribute("travelList", user.getTravelList());

        model.addAttribute("listOfShippingAddresses", true);
        model.addAttribute("classActiveShipping", true);
        model.addAttribute("listOfCreditCards", true);

        return "myProfile";
    }

    @RequestMapping(value = "/addNewShippingAddress")
    public String addShippingAddress(Model model, Principal principal) {
        model.addAttribute("title", "Add Shipping Address");

        User user = userService.findByUsername(principal.getName());
        model.addAttribute("user", user);

        model.addAttribute("addNewShippingAddress", true);
        model.addAttribute("classActiveShipping", true);
        model.addAttribute("listOfCreditCards", true);
        //model.addAttribute("listOfShippingAddresses", true);

        UserShipping userShipping = new UserShipping();

        model.addAttribute("userShipping", userShipping);

        List<String> stateList = USConstants.listOfUSStateCode;
        Collections.sort(stateList);
        model.addAttribute("stateList", stateList);

        model.addAttribute("userPaymentList", user.getUserPaymentList());
        model.addAttribute("userShippingList", user.getUserShippingList());
//        model.addAttribute("orderList", user.getOrderList());
//        model.addAttribute("travelList", user.getTravelList());

        return "myProfile";

    }
}
