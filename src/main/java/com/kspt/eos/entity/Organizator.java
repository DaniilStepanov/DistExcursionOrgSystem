package com.kspt.eos.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;


@Entity
public class Organizator {
    @Id
    @Column(name = "ID")
    @GeneratedValue
    private Long id;

    @JsonIgnore
    @OneToOne
    private User user;

    @OneToOne
    private Excursion excursion;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Excursion getExcursion() {
        return excursion;
    }

    public void setExcursion(Excursion excursion) {
        this.excursion = excursion;
    }
}
