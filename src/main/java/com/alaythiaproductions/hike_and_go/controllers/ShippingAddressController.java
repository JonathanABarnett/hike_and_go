package com.alaythiaproductions.hike_and_go.controllers;

import com.alaythiaproductions.hike_and_go.model.User;
import com.alaythiaproductions.hike_and_go.model.UserBilling;
import com.alaythiaproductions.hike_and_go.model.UserPayment;
import com.alaythiaproductions.hike_and_go.model.UserShipping;
import com.alaythiaproductions.hike_and_go.service.service.UserService;
import com.alaythiaproductions.hike_and_go.service.service.UserShippingService;
import com.alaythiaproductions.hike_and_go.utility.USConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.Collections;
import java.util.List;

@Controller
public class ShippingAddressController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserShippingService userShippingService;

    @RequestMapping(value = "/listOfShippingAddresses")
    public String listOfShippingAddresses(Model model, Principal principal) {
        model.addAttribute("title", "My Profile");

        User user = userService.findByUsername(principal.getName());
        model.addAttribute("user", user);
        model.addAttribute("userPaymentList", user.getUserPaymentList());
        model.addAttribute("userShippingList", user.getUserShippingList());
        model.addAttribute("orderList", user.getOrderList());
//        model.addAttribute("travelList", user.getTravelList());

        model.addAttribute("listOfShippingAddresses", true);
        model.addAttribute("classActiveShipping", true);
        model.addAttribute("listOfCreditCards", true);

        return "myProfile";
    }

    @GetMapping(value = "/addNewShippingAddress")
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
        model.addAttribute("orderList", user.getOrderList());
//        model.addAttribute("travelList", user.getTravelList());

        return "myProfile";

    }

    @PostMapping(value = "/addNewShippingAddress")
    public String addNewshippingAddressPost(@ModelAttribute("userShipping") UserShipping userShipping,
                                       Principal principal, Model model) {
        model.addAttribute("title", "Add Shipping Address");

        User user = userService.findByUsername(principal.getName());
        userService.updateUserShipping(userShipping, user);

        model.addAttribute("user", user);
        model.addAttribute("userPaymentList", user.getUserPaymentList());
        model.addAttribute("userShippingList", user.getUserShippingList());
        model.addAttribute("listOfCreditCards", true);
        model.addAttribute("classActiveShipping", true);
        model.addAttribute("listOfShippingAddresses", true);

        return "myProfile";
    }

    @RequestMapping(value = "/updateUserShipping")
    public String updateShippingAddress(@ModelAttribute("id") Long shippingAddressId, Principal principal, Model model) {
        model.addAttribute("title", "Update Shipping Address");
        User user = userService.findByUsername(principal.getName());
        UserShipping userShipping = userShippingService.findById(shippingAddressId);

        if (user.getId() != userShipping.getUser().getId()) {
            return "badRequestPage";
        } else {
            model.addAttribute("user", user);

            model.addAttribute("userShipping", userShipping);

            List<String> stateList = USConstants.listOfUSStateCode;
            Collections.sort(stateList);
            model.addAttribute("stateList", stateList);

            model.addAttribute("userPaymentList", user.getUserPaymentList());
            model.addAttribute("userShippingList", user.getUserShippingList());

            model.addAttribute("addNewShippingAddress", true);
            model.addAttribute("classActiveShipping", true);
            model.addAttribute("listOfCreditCards", true);
        }

        return "myProfile";
    }

    @RequestMapping(value = "/removeUserShipping")
    public String removeShippingAddress(@ModelAttribute("id") Long userShippingId, Principal principal, Model model) {
        model.addAttribute("title", "Remove Shipping Address");
        User user = userService.findByUsername(principal.getName());
        UserShipping userShipping = userShippingService.findById(userShippingId);

        if (user.getId() != userShipping.getUser().getId()) {
            return "badRequestPage";
        } else {
            model.addAttribute("user", user);
            userShippingService.removeById(userShippingId);

            model.addAttribute("userPaymentList", user.getUserPaymentList());
            model.addAttribute("userShippingList", user.getUserShippingList());

            model.addAttribute("listOfCreditCards", true);
            model.addAttribute("classActiveShipping", true);
            model.addAttribute("listOfShippingAddresses", true);
        }

        return "myProfile";
    }

    @PostMapping(value = "/setDefaultShippingAddress")
    public String setDefaultShippingAddress(@ModelAttribute("defaultShippingAddressId") Long defaultShippingId, Principal principal, Model model) {
        model.addAttribute("title", "Default Shipping Address");
        User user = userService.findByUsername(principal.getName());
        userService.setDefaultShipping(defaultShippingId, user);

        model.addAttribute("user", user);
        model.addAttribute("userPaymentList", user.getUserPaymentList());
        model.addAttribute("userShippingList", user.getUserShippingList());

        model.addAttribute("listOfCreditCards", true);
        model.addAttribute("classActiveShipping", true);
        model.addAttribute("listOfShippingAddresses", true);

        return "myProfile";
    }
}
