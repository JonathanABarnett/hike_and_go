package com.alaythiaproductions.hike_and_go.controllerAdmin;

import com.alaythiaproductions.hike_and_go.model.Product;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/admin/supply")
public class AddSupplyController {

    @RequestMapping(value = "/add")
    public String addSupply(Model model) {
        Product product = new Product();
        model.addAttribute("product", product);
        return "admin/addSupply";
    }
}
