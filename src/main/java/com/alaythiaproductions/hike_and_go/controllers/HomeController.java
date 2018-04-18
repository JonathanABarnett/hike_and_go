package com.alaythiaproductions.hike_and_go.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping(value = "/")
    public String index(Model model) {
        return "index";
    }

    @GetMapping(value = "/myAccount")
    public String myAccount(Model model) {
        return "myAccount";
    }
}
