package com.kspt.eos.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kspt.eos.logic.LExcursion;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Excursion {
    @Id
    @Column(name = "ID")
    @GeneratedValue
    private Long id;

    @Column(name = "ISPAY")
    private boolean isPay;

    @Column(name = "NAME")
    private String name;

    @Column(name = "MAXTOURISTS")
    private int maxTourists;

    @Column(name = "MINTOURISTS")
    private int minTourists;

    @Column(name = "EQUIPMENT")
    private String equipment;

    @Column(name = "STATUS")
    private int status;

    @Column(name = "DATE")
    private java.sql.Date departureDate;


    @OneToMany(fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SELECT)
    private Set<User> users = new HashSet<>();

    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="organizator_id")
    private Organizator organizator;

    @JsonIgnore
    @ManyToOne
    private Driver driver;

    @OneToOne
    private Receipt receipt;

    @JsonIgnore
    @OneToMany(fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SELECT)
    private Set<ExcursionObject> objects;

    private String description;

    public boolean isPay() {
        return isPay;
    }

    public void setPay(boolean pay) {
        isPay = pay;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMaxTourists() {
        return maxTourists;
    }

    public void setMaxTourists(int maxTourists) {
        this.maxTourists = maxTourists;
    }

    public int getMinTourists() {
        return minTourists;
    }

    public void setMinTourists(int minTourists) {
        this.minTourists = minTourists;
    }

    public String getEquipment() {
        return equipment;
    }

    public void setEquipment(String equipment) {
        this.equipment = equipment;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(Date departureDate) {
        this.departureDate = departureDate;
    }

    public Organizator getOrganizator() {
        return organizator;
    }

    public void setOrganizator(Organizator organizator) {
        this.organizator = organizator;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public Receipt getReceipt() {
        return receipt;
    }

    public void setReceipt(Receipt receipt) {
        this.receipt = receipt;
    }

    public Set<ExcursionObject> getObjects() {
        return objects;
    }

    public void setObjects(Set<ExcursionObject> objects) {
        this.objects = objects;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        LExcursion le = new LExcursion(this);
        le.updateDescription();
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
}
