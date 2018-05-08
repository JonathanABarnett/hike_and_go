package com.alaythiaproductions.hike_and_go.controllers;

import com.alaythiaproductions.hike_and_go.model.Product;
import com.alaythiaproductions.hike_and_go.model.Travel;
import com.alaythiaproductions.hike_and_go.model.User;
import com.alaythiaproductions.hike_and_go.service.service.ProductService;
import com.alaythiaproductions.hike_and_go.service.service.TravelService;
import com.alaythiaproductions.hike_and_go.service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.websocket.server.PathParam;
import java.security.Principal;
import java.util.Arrays;
import java.util.List;

@Controller
public class TravelController {

    @Autowired
    private TravelService travelService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/listOfTrips")
    public String listOfTrips(Model model) {
        model.addAttribute("title", "Travel");
        List<Travel> travelList = travelService.findAll();

        if (travelList.isEmpty()) {
            model.addAttribute("emptyList", true);
            return "tripList";
        }

        model.addAttribute("travelList", travelList);
        model.addAttribute("activeAll", true);
        return "tripList";
    }

    @RequestMapping(value = "/tripDetail")
    public String productDetail(@PathParam("id") Long id, Model model, Principal principal) {

        if (principal != null) {
            String username = principal.getName();
            User user = userService.findByUsername(username);
            model.addAttribute("user", user);
        }

        Travel travel = travelService.findOne(id);
        model.addAttribute("title", travel.getName());

        model.addAttribute("travel", travel);

        List<Integer> spotList = Arrays.asList(1,2,3,4,5,6,7,8,9,10);
        model.addAttribute("spotList", spotList);
        model.addAttribute("qty", 1);

        return "tripDetail";
    }

}
