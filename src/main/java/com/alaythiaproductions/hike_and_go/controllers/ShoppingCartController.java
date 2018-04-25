package com.alaythiaproductions.hike_and_go.controllers;

import com.alaythiaproductions.hike_and_go.model.CartItem;
import com.alaythiaproductions.hike_and_go.model.Product;
import com.alaythiaproductions.hike_and_go.model.ShoppingCart;
import com.alaythiaproductions.hike_and_go.model.User;
import com.alaythiaproductions.hike_and_go.service.service.CartItemService;
import com.alaythiaproductions.hike_and_go.service.service.ProductService;
import com.alaythiaproductions.hike_and_go.service.service.ShoppingCartService;
import com.alaythiaproductions.hike_and_go.service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping(value = "/shoppingCart")
public class ShoppingCartController {

    @Autowired
    private UserService userService;

    @Autowired
    private CartItemService cartItemService;

    @Autowired
    private ShoppingCartService shoppingCartService;

    @Autowired
    private ProductService productService;

    @RequestMapping(value = "/cart")
    public String shoppingCart(Model model, Principal principal) {
        model.addAttribute("title", "Shopping Cart");
        User user = userService.findByUsername(principal.getName());

        ShoppingCart shoppingCart = user.getShoppingCart();

        List<CartItem> cartItemList = cartItemService.findByShoppingCart(shoppingCart);

        shoppingCartService.updateShoppingCart(shoppingCart);

        model.addAttribute("cartItemList", cartItemList);
        model.addAttribute("shoppingCart", shoppingCart);

        if (shoppingCart.getCartItemList().isEmpty()) {
            model.addAttribute("emptyCart", true);
        }

        return "shoppingCart";

    }

    @RequestMapping(value = "/addProduct")
    public String addItem(@ModelAttribute("product") Product product,
                          @ModelAttribute("qty") String qty,
                          Principal principal, Model model) {
        model.addAttribute("title", "Add Item");
        User user = userService.findByUsername(principal.getName());

        product = productService.findOne(product.getId());

        if (Integer.parseInt(qty) > product.getNumberOfStock()) {
            model.addAttribute("notEnoughStock", true);
            return "forward:/productDetail?id=" + product.getId();
        }

        CartItem cartItem = cartItemService.addProductToCartItem(product, user, Integer.parseInt(qty));
        model.addAttribute("addProductSuccess", true);

        return "forward:/productDetail?id=" + product.getId();
    }

}
