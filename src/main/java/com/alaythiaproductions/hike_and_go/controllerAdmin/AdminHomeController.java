package com.alaythiaproductions.hike_and_go.controllerAdmin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/admin")
public class AdminHomeController {

    @RequestMapping(value = "/home")
    public String adminLogin(Model model) {
        model.addAttribute("title", "Admin Home");
        return "/admin/adminHome";
    }
}
