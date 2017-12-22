package com.kspt.eos.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class Receipt {

    @Id
    @Column(name = "ID")
    @GeneratedValue
    private Long id;

    @JsonIgnore
    @OneToOne
    private Excursion excursion;

    @Column(name = "SUM")
    private int sum;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Excursion getExcursion() {
        return excursion;
    }

    public void setExcursion(Excursion excursion) {
        this.excursion = excursion;
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }
}
