package com.alaythiaproductions.hike_and_go.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MyAccountHomeController {

    /**
     * Handles incoming request (GET) to the myAccount page
     *
     * @param model populate view page fields: title
     * @return the view page - myAccount.html
     */
    @GetMapping(value = "/myAccount")
    public String myAccount(Model model) {
        model.addAttribute("title", "Login/Register");
        return "myAccount";
    }
}
