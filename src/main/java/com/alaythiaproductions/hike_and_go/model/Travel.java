package com.alaythiaproductions.hike_and_go.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Travel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @OneToMany(mappedBy = "travel")
    private List<TravelToCartItem> travelToCartItemList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<TravelToCartItem> getTravelToCartItemList() {
        return travelToCartItemList;
    }

    public void setTravelToCartItemList(List<TravelToCartItem> travelToCartItemList) {
        this.travelToCartItemList = travelToCartItemList;
    }
}
