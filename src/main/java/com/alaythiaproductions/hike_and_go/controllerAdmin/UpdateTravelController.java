package com.alaythiaproductions.hike_and_go.controllerAdmin;

import com.alaythiaproductions.hike_and_go.model.Product;
import com.alaythiaproductions.hike_and_go.model.Travel;
import com.alaythiaproductions.hike_and_go.service.service.ProductService;
import com.alaythiaproductions.hike_and_go.service.service.TravelService;
import com.alaythiaproductions.hike_and_go.utility.USConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping(value = "/admin")
public class UpdateTravelController {

    @Autowired
    private TravelService travelService;

    @GetMapping(value = "/updateTravel")
    public String updateTravel(@RequestParam("id") Long id, Model model) {
        Travel travel = travelService.findOne(id);
        model.addAttribute("travel", travel);

        List<String> stateList = USConstants.listOfUSStateCode;
        Collections.sort(stateList);
        model.addAttribute("stateList", stateList);

        return "updateTravel";
    }

    @PostMapping(value = "/updateTravel")
    public String updateTravelPost(@ModelAttribute("travel") Travel travel,
                                   Model model, HttpServletRequest request) {
        travelService.save(travel);
        return "redirect:travelInfo?id=" + travel.getId();
    }
}
