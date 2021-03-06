package com.alaythiaproductions.hike_and_go.controllerAdmin;

import com.alaythiaproductions.hike_and_go.model.Product;
import com.alaythiaproductions.hike_and_go.model.Travel;
import com.alaythiaproductions.hike_and_go.service.service.ProductService;
import com.alaythiaproductions.hike_and_go.service.service.TravelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(value = "/admin")
public class AdminHomeController {

    @Autowired
    private ProductService productService;

    @Autowired
    private TravelService travelService;

    @RequestMapping(value = "/home")
    public String adminLogin(Model model) {
        model.addAttribute("title", "Admin Home");
        return "adminHome";
    }

    @RequestMapping(value = "/supply")
    public String viewSupply(Model model) {
        model.addAttribute("title", "Supply List");
        List<Product> productList = productService.findAll();
        model.addAttribute("productList", productList);
        return "supplyList";
    }

    @RequestMapping(value = "/travel")
    public String viewTravel(Model model) {
        model.addAttribute("title", "Travel List");
        List<Travel> travelList = travelService.findAll();
        model.addAttribute("travelList", travelList);
        return "travelList";
    }
}
