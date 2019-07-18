package com.thoughtworks.parking_lot.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Min;

@Entity
public class ParkingLot {
    @Id
    private String name;
    @Min(value =0)
    private int capacity;
    private int address;

    public ParkingLot() {
    }

    public ParkingLot(String name, @Min(value = 0) int capacity, int address) {
        this.name = name;
        this.capacity = capacity;
        this.address = address;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void setAddress(int address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getAddress() {
        return address;
    }
}
