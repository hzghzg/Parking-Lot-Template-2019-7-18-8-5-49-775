package com.thoughtworks.parking_lot.entity;

import javax.persistence.*;

@Entity
public class ParkingOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ordernumber;
    @Column
    private String parkingLotName;
    @Column
    private String licensePlateNumber;
    @Column
    private Long beginTime;
    @Column
    private Long endTime;
    @Column
    private String orderStatus;

    public ParkingOrder() {
    }

    public void setOrdernumber(Long ordernumber) {
        this.ordernumber = ordernumber;
    }

    public void setParkingLotName(String parkingLotName) {
        this.parkingLotName = parkingLotName;
    }

    public void setLicensePlateNumber(String licensePlateNumber) {
        this.licensePlateNumber = licensePlateNumber;
    }

    public void setBeginTime(Long beginTime) {
        this.beginTime = beginTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Long getOrdernumber() {
        return ordernumber;
    }

    public String getParkingLotName() {
        return parkingLotName;
    }

    public String getLicensePlateNumber() {
        return licensePlateNumber;
    }

    public Long getBeginTime() {
        return beginTime;
    }

    public Long getEndTime() {
        return endTime;
    }

    public String getOrderStatus() {
        return orderStatus;
    }
}
