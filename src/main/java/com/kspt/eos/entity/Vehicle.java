package com.kspt.eos.entity;

import javax.persistence.*;

@Entity
public class Vehicle {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "NUMBERS", unique = true, nullable = false)
    private String numbers;

    @Column(name = "MODEL")
    private String model;

    @Column(name = "MILEAGE")
    private int milage;

    @Column(name = "CAPACITY")
    private int capacity;

    @Column(name = "ISCHECKED")
    private boolean isChecked;


    public Long getId() {
        return id;
    }

    public String getNumbers() {
        return numbers;
    }

    public String getModel() {
        return model;
    }

    public int getMilage() {
        return milage;
    }

    public int getCapacity() {
        return capacity;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setMilage(int milage) {
        this.milage = milage;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public void setNumbers(String numbers) {
        this.numbers = numbers;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void setId(Long id) {this.id = id;}

}
