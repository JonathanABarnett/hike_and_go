package com.alaythiaproductions.hike_and_go.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MyProfileController {

    @RequestMapping(value = "/myProfile")
    public String myProfile() {

        return "myProfile";
    }
}
