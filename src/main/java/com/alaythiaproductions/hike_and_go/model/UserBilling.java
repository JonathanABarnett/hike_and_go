package com.alaythiaproductions.hike_and_go.model;

import javax.persistence.*;

@Entity
public class UserBilling {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userBillingFirstName;
    private String userBillingLastName;
    private String userBillingStreet1;
    private String userBillingStreet2;
    private String userBillingCity;
    private String userBillingState;
    private String userBillingZipCode;

    @OneToOne(cascade = CascadeType.ALL)
    private UserPayment userPayment;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserBillingFirstName() {
        return userBillingFirstName;
    }

    public void setUserBillingFirstName(String userBillingFirstName) {
        this.userBillingFirstName = userBillingFirstName;
    }

    public String getUserBillingLastName() {
        return userBillingLastName;
    }

    public void setUserBillingLastName(String userBillingLastName) {
        this.userBillingLastName = userBillingLastName;
    }

    public String getUserBillingStreet1() {
        return userBillingStreet1;
    }

    public void setUserBillingStreet1(String userBillingStreet1) {
        this.userBillingStreet1 = userBillingStreet1;
    }

    public String getUserBillingStreet2() {
        return userBillingStreet2;
    }

    public void setUserBillingStreet2(String userBillingStreet2) {
        this.userBillingStreet2 = userBillingStreet2;
    }

    public String getUserBillingCity() {
        return userBillingCity;
    }

    public void setUserBillingCity(String userBillingCity) {
        this.userBillingCity = userBillingCity;
    }

    public String getUserBillingState() {
        return userBillingState;
    }

    public void setUserBillingState(String userBillingState) {
        this.userBillingState = userBillingState;
    }

    public String getUserBillingZipCode() {
        return userBillingZipCode;
    }

    public void setUserBillingZipCode(String userBillingZipCode) {
        this.userBillingZipCode = userBillingZipCode;
    }

    public UserPayment getUserPayment() {
        return userPayment;
    }

    public void setUserPayment(UserPayment userPayment) {
        this.userPayment = userPayment;
    }
}
