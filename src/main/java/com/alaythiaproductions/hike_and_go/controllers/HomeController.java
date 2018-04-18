package com.alaythiaproductions.hike_and_go.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Home Controller used for handling incoming requests to the index.html page
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
        model.addAttribute("title", "Hike &amp; Go!");
        return "index";
    }

}
