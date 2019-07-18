package com.thoughtworks.parking_lot.controller;

import com.thoughtworks.parking_lot.entity.ParkingLot;
import com.thoughtworks.parking_lot.repository.ParkingLotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/parkinglots")
public class ParkingLotController {
    @Autowired
    ParkingLotRepository parkingLotRepository;
    List<ParkingLot> parkingLotList=new ArrayList<>();
    //story AC1
    @PostMapping
    public List<ParkingLot> addParkingLot(@RequestBody ParkingLot parkingLot){
        parkingLotList.add(parkingLot);
        parkingLotRepository.save(parkingLot);
        parkingLotList=parkingLotRepository.findAll();
        return parkingLotList;
    }

}
