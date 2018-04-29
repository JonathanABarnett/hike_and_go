package com.alaythiaproductions.hike_and_go.controllers;

import com.alaythiaproductions.hike_and_go.model.Product;
import com.alaythiaproductions.hike_and_go.model.User;
import com.alaythiaproductions.hike_and_go.service.service.ProductService;
import com.alaythiaproductions.hike_and_go.service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.awt.print.Book;
import java.security.Principal;
import java.util.List;

@Controller
public class SearchController {

    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;

    @GetMapping(value = "/searchByCategory")
    public String searchByCategory(@RequestParam("category") String category, Model model, Principal principal){

        String classActiveCategory = "active" + category;
        System.out.println(classActiveCategory);
        classActiveCategory = classActiveCategory.replaceAll("\\s+", "");
        classActiveCategory = classActiveCategory.replaceAll("&", "");

        model.addAttribute(classActiveCategory, true);

        List<Product> productList = productService.findByCategory(category);

        if (productList.isEmpty()) {
            model.addAttribute("emptyList", true);
            return "productList";
        }

        model.addAttribute("productList", productList);

        return "productList";
    }

    @PostMapping(value = "/searchProduct")
    public String searchBook(@ModelAttribute("keyword") String keyword, Principal principal, Model model) {

        System.out.println("Keyword:"  + keyword);

        List<Product> productList = productService.blurrySearch(keyword);

        for (Product product : productList) {
            System.out.println(product.getName());
        }

        if (productList.isEmpty()) {
            model.addAttribute("emptyList", true);
            return "productList";
        }

        model.addAttribute("productList", productList);

        return "productList";
    }
}
