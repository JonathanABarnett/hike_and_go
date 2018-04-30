package com.alaythiaproductions.hike_and_go.controllerAdmin;

import com.alaythiaproductions.hike_and_go.service.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class ResourceController {

    @Autowired
    private ProductService productService;

    @PostMapping(value = "/removeList")
    public String removeList(@RequestBody ArrayList<String> productIdList, Model model) {
        for (String id : productIdList) {
            String productId = id.substring(8);
            System.out.println(productId);
            productService.removeOne(Long.parseLong(productId));
        }

        return "supplyList";
    }
}
