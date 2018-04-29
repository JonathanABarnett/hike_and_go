package com.alaythiaproductions.hike_and_go.controllers;

import com.alaythiaproductions.hike_and_go.model.Product;
import com.alaythiaproductions.hike_and_go.model.User;
import com.alaythiaproductions.hike_and_go.service.service.ProductService;
import com.alaythiaproductions.hike_and_go.service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.websocket.server.PathParam;
import java.lang.reflect.Array;
import java.security.Principal;
import java.util.Arrays;
import java.util.List;

@Controller
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/listOfProducts")
    public String listOfProducts(Model model) {
        model.addAttribute("title", "Supplies");
        List<Product> productList = productService.findAll();

        if (productList.isEmpty()) {
            model.addAttribute("emptyList", true);
            return "/productList";
        }

        model.addAttribute("productList", productList);
        model.addAttribute("activeAll", true);
        return "/productList";
    }

    @RequestMapping(value = "/productDetail")
    public String productDetail(@PathParam("id") Long id, Model model, Principal principal) {

        if (principal != null) {
            String username = principal.getName();
            User user = userService.findByUsername(username);
            model.addAttribute("user", user);
        }

        Product product = productService.findOne(id);
        model.addAttribute("title", product.getName());

        model.addAttribute("product", product);

        List<Integer> qtyList = Arrays.asList(1,2,3,4,5,6,7,8,9,10);
        model.addAttribute("qtyList", qtyList);
        model.addAttribute("qty", 1);

        return "productDetail";
    }
}
