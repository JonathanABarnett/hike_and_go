package com.alaythiaproductions.hike_and_go.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Travel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String startDate;
    private String endDate;
    private int numOfDays;
    private int maxPeople;
    private String location;
    private String experience;
    private ArrayList<String> features;
    @Column(columnDefinition = "text")
    private String imgUrl;
    @Column(columnDefinition = "text")
    private String videoUrl;
    @Column(columnDefinition = "text")
    private String description;
    private double ourPrice;
    private boolean active = true;

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

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public int getNumOfDays() {
        return numOfDays;
    }

    public void setNumOfDays(int numOfDays) {
        this.numOfDays = numOfDays;
    }

    public int getMaxPeople() {
        return maxPeople;
    }

    public void setMaxPeople(int maxPeople) {
        this.maxPeople = maxPeople;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public ArrayList<String> getFeatures() {
        return features;
    }

    public void setFeatures(ArrayList<String> features) {
        this.features = features;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getOurPrice() {
        return ourPrice;
    }

    public void setOurPrice(double ourPrice) {
        this.ourPrice = ourPrice;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public List<TravelToCartItem> getTravelToCartItemList() {
        return travelToCartItemList;
    }

    public void setTravelToCartItemList(List<TravelToCartItem> travelToCartItemList) {
        this.travelToCartItemList = travelToCartItemList;
    }


}
