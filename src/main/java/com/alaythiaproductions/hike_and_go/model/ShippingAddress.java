package com.alaythiaproductions.hike_and_go.model;

import javax.persistence.*;

@Entity
public class ShippingAddress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String shippingAddressFirstName;
    private String shippingAddressLastName;
    private String shippingAddressStreet1;
    private String shippingAddressStreet2;
    private String shippingAddressCity;
    private String shippingAddressState;
    private String shippingAddressZipCode;
    private boolean shippingAddressDefault;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getShippingAddressFirstName() {
        return shippingAddressFirstName;
    }

    public void setShippingAddressFirstName(String shippingAddressFirstName) {
        this.shippingAddressFirstName = shippingAddressFirstName;
    }

    public String getShippingAddressLastName() {
        return shippingAddressLastName;
    }

    public void setShippingAddressLastName(String shippingAddressLastName) {
        this.shippingAddressLastName = shippingAddressLastName;
    }

    public String getShippingAddressStreet1() {
        return shippingAddressStreet1;
    }

    public void setShippingAddressStreet1(String shippingAddressStreet1) {
        this.shippingAddressStreet1 = shippingAddressStreet1;
    }

    public String getShippingAddressStreet2() {
        return shippingAddressStreet2;
    }

    public void setShippingAddressStreet2(String shippingAddressStreet2) {
        this.shippingAddressStreet2 = shippingAddressStreet2;
    }

    public String getShippingAddressCity() {
        return shippingAddressCity;
    }

    public void setShippingAddressCity(String shippingAddressCity) {
        this.shippingAddressCity = shippingAddressCity;
    }

    public String getShippingAddressState() {
        return shippingAddressState;
    }

    public void setShippingAddressState(String shippingAddressState) {
        this.shippingAddressState = shippingAddressState;
    }

    public String getShippingAddressZipCode() {
        return shippingAddressZipCode;
    }

    public void setShippingAddressZipCode(String shippingAddressZipCode) {
        this.shippingAddressZipCode = shippingAddressZipCode;
    }

    public boolean isShippingAddressDefault() {
        return shippingAddressDefault;
    }

    public void setShippingAddressDefault(boolean shippingAddressDefault) {
        this.shippingAddressDefault = shippingAddressDefault;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
