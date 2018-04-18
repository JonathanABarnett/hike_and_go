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
        model.addAttribute("title", "Login");
        model.addAttribute("classActiveLogin", true);
        return "myAccount";
    }

    @GetMapping(value = "/login")
    public String login(Model model) {
        model.addAttribute("title", "Login");
        model.addAttribute("classActiveLogin", true);
        return "myAccount";
    }

    @GetMapping(value = "/register")
    public String register(Model model) {
        model.addAttribute("title", "Register");
        model.addAttribute("classActiveRegister", true);
        return "myAccount";
    }

    @GetMapping(value = "/forgot")
    public String forgot(Model model) {
        model.addAttribute("title", "Forgot Password");
        model.addAttribute("classActiveForgot", true);
        return "myAccount";
    }
}
