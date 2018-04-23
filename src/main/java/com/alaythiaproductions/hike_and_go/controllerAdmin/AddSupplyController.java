package com.alaythiaproductions.hike_and_go.controllerAdmin;

import com.alaythiaproductions.hike_and_go.model.Product;
import com.alaythiaproductions.hike_and_go.repository.ProductRepository;
import com.alaythiaproductions.hike_and_go.service.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "/admin/supply")
public class AddSupplyController {

    @Autowired
    private ProductService productService;

    @GetMapping(value = "/add")
    public String addSupply(Model model) {
        model.addAttribute("title", "Add Product");
        Product product = new Product();
        model.addAttribute("product", product);
        return "admin/addSupply";
    }

    @PostMapping(value = "/add")
    public String addSupplyPost(Model model, @ModelAttribute("product") Product product, HttpServletRequest request) {
        model.addAttribute("title", "Add Product");

        productService.save(product);
        return "redirect:/admin/supply";
    }



}
