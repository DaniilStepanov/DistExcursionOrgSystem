package com.kspt.eos.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class Driver {
    @Id
    @Column(name = "ID")
    @GeneratedValue
    private Long id;

    @Column(name = "ISFREE")
    private boolean isFree;

    @Column(name = "ISAGREE")
    private boolean isAgree;

    @Column(name = "GIVENPRICE")
    private int givenPrice;

    @JsonIgnore
    @OneToOne
    private User user;

    @JsonIgnore
    @OneToOne
    private Vehicle vehicle;

    @JsonIgnore
    @OneToOne
    private Excursion excursion;

    public boolean isFree() {
        return isFree;
    }

    public void setFree(boolean free) {
        isFree = free;
    }

    public boolean isAgree() {
        return isAgree;
    }

    public void setAgree(boolean agree) {
        isAgree = agree;
    }

    public int getGivenPrice() {
        return givenPrice;
    }

    public void setGivenPrice(int givenPrice) {
        this.givenPrice = givenPrice;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public Excursion getExcursion() {
        return excursion;
    }

    public void setExcursion(Excursion excursion) {
        this.excursion = excursion;
    }
}
