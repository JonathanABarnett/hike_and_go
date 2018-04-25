package com.alaythiaproductions.hike_and_go.model;

import javax.persistence.*;

@Entity
public class BillingAddress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String billingAddressFirstName;
    private String billingAddressLastName;
    private String billingAddressStreet1;
    private String billingAddressStreet2;
    private String billingAddressCity;
    private String billingAddressState;
    private String billingAddressZipCode;

    @OneToOne
    private Order order;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBillingAddressFirstName() {
        return billingAddressFirstName;
    }

    public void setBillingAddressFirstName(String billingAddressFirstName) {
        this.billingAddressFirstName = billingAddressFirstName;
    }

    public String getBillingAddressLastName() {
        return billingAddressLastName;
    }

    public void setBillingAddressLastName(String billingAddressLastName) {
        this.billingAddressLastName = billingAddressLastName;
    }

    public String getBillingAddressStreet1() {
        return billingAddressStreet1;
    }

    public void setBillingAddressStreet1(String billingAddressStreet1) {
        this.billingAddressStreet1 = billingAddressStreet1;
    }

    public String getBillingAddressStreet2() {
        return billingAddressStreet2;
    }

    public void setBillingAddressStreet2(String billingAddressStreet2) {
        this.billingAddressStreet2 = billingAddressStreet2;
    }

    public String getBillingAddressCity() {
        return billingAddressCity;
    }

    public void setBillingAddressCity(String billingAddressCity) {
        this.billingAddressCity = billingAddressCity;
    }

    public String getBillingAddressState() {
        return billingAddressState;
    }

    public void setBillingAddressState(String billingAddressState) {
        this.billingAddressState = billingAddressState;
    }

    public String getBillingAddressZipCode() {
        return billingAddressZipCode;
    }

    public void setBillingAddressZipCode(String billingAddressZipCode) {
        this.billingAddressZipCode = billingAddressZipCode;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
