package com.alaythiaproductions.hike_and_go.controllerAdmin;

import com.alaythiaproductions.hike_and_go.model.Product;
import com.alaythiaproductions.hike_and_go.model.Travel;
import com.alaythiaproductions.hike_and_go.service.service.ProductService;
import com.alaythiaproductions.hike_and_go.service.service.TravelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "/admin")
public class UpdateTravelController {

    @Autowired
    private TravelService travelService;

    @GetMapping(value = "/updateTravel")
    public String updateTravel(@RequestParam("id") Long id, Model model) {
        Travel travel = travelService.findOne(id);
        model.addAttribute("travel", travel);
        return "updateTravel";
    }

    @PostMapping(value = "/updateTravel")
    public String updateTravelPost(@ModelAttribute("travel") Travel travel, Model model, HttpServletRequest request) {
        travelService.save(travel);
        model.addAttribute("title", travel.getName());
        return "redirect:travelInfo?id=" + travel.getId();
    }
}
