package com.alaythiaproductions.hike_and_go.controllerAdmin;

import com.alaythiaproductions.hike_and_go.model.Product;
import com.alaythiaproductions.hike_and_go.model.Travel;
import com.alaythiaproductions.hike_and_go.service.service.ProductService;
import com.alaythiaproductions.hike_and_go.service.service.TravelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(value = "/admin")
public class DeleteTravelController {

    @Autowired
    private TravelService travelService;

    @PostMapping(value = "/removeTravel")
    public String remove(@ModelAttribute("id") String id, Model model){
        travelService.removeOne(Long.parseLong(id.substring(10)));
        List<Travel> travelList = travelService.findAll();
        model.addAttribute("travelList", travelList);

        return "redirect:travel";
    }
}
