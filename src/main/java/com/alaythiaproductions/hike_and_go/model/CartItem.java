package com.alaythiaproductions.hike_and_go.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int qty;

    private BigDecimal subTotal;

    @OneToOne
    private Product product;

    @OneToOne
    private Travel travel;

    @OneToMany(mappedBy = "cartItem")
    private List<ProductToCartItem> productToCartItemList;

    @OneToMany(mappedBy = "cartItem")
    private List<TravelToCartItem> travelToCartItemList;

    @ManyToOne
    @JoinColumn(name = "shopping_cart_id")
    private ShoppingCart shoppingCart;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;
}
