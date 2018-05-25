package com.alaythiaproductions.hike_and_go.controllers;

import com.alaythiaproductions.hike_and_go.model.Product;
import com.alaythiaproductions.hike_and_go.service.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Home Controller used for handling incoming requests to the index.html page
 * and static navigation links.
 */

@Controller
public class HomeController {

    @Autowired
    private ProductService productService;

    /**
     * Handles incoming request (GET) to the home page
     *
     * @param model populate view page fields. Specifically the title of the page
     * @return the view page - index.html
     */
    @GetMapping(value = "/")
    public String index(Model model) {
        model.addAttribute("title", "Hike & Go!");

        Random random = new Random();

        List<Product> productList = productService.findByCategory("Backpacks");

        List<Product> specialList = new ArrayList<>();

        // For random specials on home page
       /* long maxId = 0;

        for (Product product : productList) {
            if (product.getId() > maxId) {
                maxId = product.getId();
            }
        }

        for (int i = 0; i < 4; i++) {

            int id = random.nextInt((int)maxId + 1);
            Long longId = Long.valueOf(id);
            Product backpack = productService.findOne(longId);
            if (backpack != null && !specialList.contains(backpack)) {
                specialList.add(backpack);
            } else {
                i--;
            }
        } */

        /* Backpack specials
        specialList.add(productService.findOne(1L));
        specialList.add(productService.findOne(2L));
        specialList.add(productService.findOne(3L));
        specialList.add(productService.findOne(4L));
    */
        specialList.add(productService.findOne(1L));
        specialList.add(productService.findOne(2L));
        specialList.add(productService.findOne(3L));
        specialList.add(productService.findOne(4L));

        model.addAttribute("specialList", specialList);

        return "index";
    }

    @GetMapping(value = "/about")
    public String about(Model model) {
        model.addAttribute("title", "About Us");
        return "about";
    }



}
