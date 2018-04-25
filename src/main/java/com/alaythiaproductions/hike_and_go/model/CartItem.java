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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public BigDecimal getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(BigDecimal subTotal) {
        this.subTotal = subTotal;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Travel getTravel() {
        return travel;
    }

    public void setTravel(Travel travel) {
        this.travel = travel;
    }

    public List<ProductToCartItem> getProductToCartItemList() {
        return productToCartItemList;
    }

    public void setProductToCartItemList(List<ProductToCartItem> productToCartItemList) {
        this.productToCartItemList = productToCartItemList;
    }

    public List<TravelToCartItem> getTravelToCartItemList() {
        return travelToCartItemList;
    }

    public void setTravelToCartItemList(List<TravelToCartItem> travelToCartItemList) {
        this.travelToCartItemList = travelToCartItemList;
    }

    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    public void setShoppingCart(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
