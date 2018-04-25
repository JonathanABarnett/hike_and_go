package com.alaythiaproductions.hike_and_go.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Home Controller used for handling incoming requests to the index.html page
 * and static navigation links.
 */

@Controller
public class HomeController {

    /**
     * Handles incoming request (GET) to the home page
     *
     * @param model populate view page fields. Specifically the title of the page
     * @return the view page - index.html
     */
    @GetMapping(value = "/")
    public String index(Model model) {
        model.addAttribute("title", "Hike & Go!");
        return "index";
    }

    @GetMapping(value = "/about")
    public String about(Model model) {
        model.addAttribute("title", "About Us");
        return "about";
    }

    @GetMapping(value = "/contactUs")
    public String contactUs(Model model) {
        model.addAttribute("title", "Contact Us");
        return "contactUs";
    }


}
