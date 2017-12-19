package com.kspt.eos.entity;

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
    java.sql.Date departureDate;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "EXCURSION_PARTICIPANTS",
            joinColumns = { @JoinColumn(name = "excursion") },
            inverseJoinColumns = { @JoinColumn(name = "user") }
    )
    private ArrayList<User> travellers = new ArrayList<>();

    @OneToOne
    private Organizator organizator;

    @ManyToOne
    private Driver driver;

    @OneToOne
    private Receipt receipt;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "id")
    private Set<ExcursionObject> objects;

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

    public ArrayList<User> getTravellers() {
        return travellers;
    }

    public void setTravellers(ArrayList<User> travellers) {
        this.travellers = travellers;
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
}
