package com.thoughtworks.parking_lot.controller;

import com.thoughtworks.parking_lot.entity.ParkingLot;
import com.thoughtworks.parking_lot.repository.ParkingLotRepository;
import com.thoughtworks.parking_lot.service.ParkingLotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/parkinglots")
public class ParkingLotController {
    @Autowired
    ParkingLotRepository parkingLotRepository;
    @Autowired
    private ParkingLotService parkingLotService;
    List<ParkingLot> parkingLotList=new ArrayList<>();
    //story AC1
    @PostMapping
    public List<ParkingLot> addParkingLot(@RequestBody ParkingLot parkingLot){
        parkingLotList.add(parkingLot);
        parkingLotRepository.save(parkingLot);
        parkingLotList=parkingLotRepository.findAll();
        return parkingLotList;
    }
    //story AC2
    @DeleteMapping("/{parkinglotname}")
    public List<ParkingLot> deleteParkingLot(@PathVariable("parkinglotname") String parkinglotname){
        parkingLotRepository.deleteById(parkinglotname);
        return parkingLotRepository.findAll();
    }
    @GetMapping("/pageNumber/{pageNumber}/pageSize/{pageSize}")
    public List<ParkingLot> findParkingLotByPageSize(@PathVariable int pageNumber, @PathVariable int pageSize) {
        List<ParkingLot> parkingLotList1=parkingLotService.findAllParkingLotsByPageSize(pageNumber, pageSize);
        return  parkingLotList1;
    }
    @GetMapping("/{parkinglotname}")
    public ParkingLot findParkingLotByName(@PathVariable("parkinglotname") String parkinglotname) {
        ParkingLot parkingLot=parkingLotRepository.findById(parkinglotname).get();
        return  parkingLot;
    }

}
