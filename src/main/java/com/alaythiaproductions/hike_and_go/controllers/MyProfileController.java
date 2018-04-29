package com.alaythiaproductions.hike_and_go.controllers;

import com.alaythiaproductions.hike_and_go.model.User;
import com.alaythiaproductions.hike_and_go.model.UserBilling;
import com.alaythiaproductions.hike_and_go.model.UserPayment;
import com.alaythiaproductions.hike_and_go.model.UserShipping;
import com.alaythiaproductions.hike_and_go.service.implementation.UserSecurityService;
import com.alaythiaproductions.hike_and_go.service.service.UserService;
import com.alaythiaproductions.hike_and_go.utility.SecurityUtility;
import com.alaythiaproductions.hike_and_go.utility.USConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Controller
public class MyProfileController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserSecurityService userSecurityService;

    @RequestMapping(value = "/myProfile")
    public String myProfile(Model model, Principal principal) {
        model.addAttribute("title", "My Profile");

        User user = userService.findByUsername(principal.getName());
        model.addAttribute("user", user);

        model.addAttribute("userPaymentList", user.getUserPaymentList());
        model.addAttribute("userShippingList", user.getUserShippingList());
        model.addAttribute("orderList", user.getOrderList());
//        model.addAttribute("travelList", user.getTravelList());

        UserShipping userShipping = new UserShipping();
        model.addAttribute("userShipping", userShipping);

        model.addAttribute("listOfCreditCards", true);
        model.addAttribute("listOfShippingAddresses", true);

        List<String> stateList = USConstants.listOfUSStateCode;
        Collections.sort(stateList);
        model.addAttribute("stateList", stateList);

        model.addAttribute("classActiveEdit", true);

        return "myProfile";
    }

    @PostMapping(value = "/updateUserInfo")
    public String updateUserInfo(@ModelAttribute("user") User user,
                                 @ModelAttribute("newPassword") String newPassword,
                                 Model model) throws Exception{
        System.out.println("Here");

        User currentUser = userService.findById(user.getId());
        System.out.println("User: " + user.getFirstName());

        if (currentUser == null) {
            throw new Exception("User not found");
        }

        if (userService.findByEmail(user.getEmail()) != null) {
            if(userService.findByEmail(user.getEmail()).getId() != currentUser.getId()) {
                model.addAttribute("emailExists", true);
                model.addAttribute("classActiveEdit", true);
                return "myProfile";
            }
        }

        if (userService.findByUsername(user.getUsername()) != null) {
            if(userService.findByUsername(user.getUsername()).getId() != currentUser.getId()) {
                model.addAttribute("usernameExists", true);
                model.addAttribute("classActiveEdit", true);
                return "myProfile";
            }
        }

        if (newPassword != null && !newPassword.isEmpty() && !newPassword.equals("")){
            BCryptPasswordEncoder passwordEncoder = SecurityUtility.passwordEncoder();
            String dbPassword = currentUser.getPassword();
            if (passwordEncoder.matches(user.getPassword(), dbPassword)) {
                currentUser.setPassword(passwordEncoder.encode(newPassword));
            } else {
                model.addAttribute("incorrectPassword", true);
                model.addAttribute("classActiveEdit", true);
                return "myProfile";
            }
        }

        currentUser.setFirstName(user.getFirstName());
        currentUser.setLastName(user.getLastName());
        currentUser.setUsername(user.getUsername());
        currentUser.setEmail(user.getEmail());

        userService.save(currentUser);

        model.addAttribute("listOfCreditCards", true);
        model.addAttribute("listOfShippingAddresses", true);

        model.addAttribute("updateUserInfoSuccess", true);
        model.addAttribute("user", currentUser);
        model.addAttribute("classActiveEdit", true);

        UserDetails userDetails = userSecurityService.loadUserByUsername(currentUser.getUsername());

        Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(),
                userDetails.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(authentication);

        return "myProfile";
    }

}
