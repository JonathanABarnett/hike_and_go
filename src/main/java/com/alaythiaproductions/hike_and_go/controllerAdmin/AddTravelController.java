package com.alaythiaproductions.hike_and_go.controllerAdmin;

import com.alaythiaproductions.hike_and_go.model.Product;
import com.alaythiaproductions.hike_and_go.model.Travel;
import com.alaythiaproductions.hike_and_go.service.service.ProductService;
import com.alaythiaproductions.hike_and_go.service.service.TravelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "/admin")
public class AddTravelController {

    @Autowired
    private TravelService travelService;

    @GetMapping(value = "/travel/add")
    public String addSupply(Model model) {
        model.addAttribute("title", "Add Trip");
        Travel travel = new Travel();
        model.addAttribute("travel", travel);
        return "addTravel";
    }

    @PostMapping(value = "/travel/add")
    public String addSupplyPost(Model model, @ModelAttribute("travel") Travel travel, HttpServletRequest request) {
        model.addAttribute("title", "Add Trip");

        travelService.save(travel);
        return "redirect:/admin/travel";
    }
}
