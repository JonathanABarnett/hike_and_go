package com.alaythiaproductions.hike_and_go.controllerAdmin;

import com.alaythiaproductions.hike_and_go.model.Product;
import com.alaythiaproductions.hike_and_go.service.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.persistence.Id;
import java.awt.print.Book;
import java.util.List;

@Controller
@RequestMapping(value = "/admin")
public class DeleteSupplyController {

    @Autowired
    private ProductService productService;

    @PostMapping(value = "/remove")
    public String remove(@ModelAttribute("id") String id, Model model){
        productService.removeOne(Long.parseLong(id.substring(11)));
        List<Product> productList = productService.findAll();
        model.addAttribute("productList", productList);

        return "redirect:supply";
    }
}
