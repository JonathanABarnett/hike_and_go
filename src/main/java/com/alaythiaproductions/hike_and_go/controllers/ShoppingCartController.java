package com.alaythiaproductions.hike_and_go.controllers;

import com.alaythiaproductions.hike_and_go.model.*;
import com.alaythiaproductions.hike_and_go.service.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    @Autowired
    private TravelService travelService;

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

//    @RequestMapping(value = "/addTravel")
//    public String addItem(@ModelAttribute("trip") Travel trip,
//                          @ModelAttribute("qty") String qty,
//                          Principal principal, Model model) {
//        model.addAttribute("title", "Add Trip");
//        User user = userService.findByUsername(principal.getName());
//
//        trip = travelService.findOne(trip.getId());
//
//        if (Integer.parseInt(qty) > trip.getMaxPeople()) {
//            model.addAttribute("notEnoughSpots", true);
//            return "forward:/tripDetail?id=" + trip.getId();
//        }
//
//        CartItem cartItem = cartItemService.addTravelToCartItem(trip, user, Integer.parseInt(qty));
//        model.addAttribute("addTravelSuccess", true);
//
//        return "forward:/tripDetail?id=" + trip.getId();
//    }

    @RequestMapping(value = "/updateCartItem")
    public String updateShoppingCart(Model model, @ModelAttribute("id") Long cartItemId,
                                     @ModelAttribute("qty") int qty) {
        CartItem cartItem = cartItemService.findById(cartItemId);
        model.addAttribute("title", "Update " + cartItem.getProduct().getName());
        cartItem.setQty(qty);
        cartItemService.updateCartItem(cartItem);

        return "forward:/shoppingCart/cart";
    }

    @RequestMapping(value = "/removeProduct")
    public String removeProduct(@RequestParam("id") Long id) {
        cartItemService.removeCartItem(cartItemService.findById(id));

        return "forward:/shoppingCart/cart";
    }

    @RequestMapping(value = "/removeTravel")
    public String removeTravel(@RequestParam("id") Long id) {
        cartItemService.removeCartItem(cartItemService.findById(id));

        return "forward:/shoppingCart/cart";
    }

}
