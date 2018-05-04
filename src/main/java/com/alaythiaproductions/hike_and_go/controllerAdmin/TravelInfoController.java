package com.alaythiaproductions.hike_and_go.controllerAdmin;

import com.alaythiaproductions.hike_and_go.model.Product;
import com.alaythiaproductions.hike_and_go.model.Travel;
import com.alaythiaproductions.hike_and_go.service.service.ProductService;
import com.alaythiaproductions.hike_and_go.service.service.TravelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = "/admin")
public class TravelInfoController {

    @Autowired
    private TravelService travelService;

    @RequestMapping(value = "/travelInfo")
    public String travelInfo(@RequestParam("id") Long id, Model model) {
        Travel travel = travelService.findOne(id);
        model.addAttribute("title", travel.getName());
        model.addAttribute("travel", travel);
        return "travelInfo";
    }
}
