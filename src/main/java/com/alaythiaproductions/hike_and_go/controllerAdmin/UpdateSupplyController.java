package com.alaythiaproductions.hike_and_go.controllerAdmin;

import com.alaythiaproductions.hike_and_go.model.Product;
import com.alaythiaproductions.hike_and_go.service.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "/admin")
public class UpdateSupplyController {

    @Autowired
    private ProductService productService;

    @GetMapping(value = "/update")
    public String updateSupply(@RequestParam("id") Long id, Model model) {
        Product product = productService.findOne(id);
        model.addAttribute("product", product);
        return "admin/updateSupply";
    }

    @PostMapping(value = "/update")
    public String updateSupplyPost(@ModelAttribute("product") Product product, Model model, HttpServletRequest request) {
        productService.save(product);
        model.addAttribute("title", product.getName());
        return "redirect:/admin/supplyInfo?id=" + product.getId();
    }
}
