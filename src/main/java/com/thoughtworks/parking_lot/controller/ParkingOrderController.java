package com.thoughtworks.parking_lot.controller;

import com.thoughtworks.parking_lot.entity.ParkingLot;
import com.thoughtworks.parking_lot.entity.ParkingOrder;
import com.thoughtworks.parking_lot.repository.ParkingLotRepository;
import com.thoughtworks.parking_lot.repository.ParkingOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
@RestController
@RequestMapping("/parkingorder")
public class ParkingOrderController {
    @Autowired
    private ParkingOrderRepository parkingOrderRepository;
    @Autowired
    private ParkingLotRepository parkingLotRepository;
    @PostMapping
    public String addOrders(@RequestBody ParkingOrder parkingOrder){
        String parkingLotName=parkingOrder .getParkingLotName();
        ParkingLot parkingLot=parkingLotRepository.findById(parkingLotName).get();
        int totalCapacity=parkingLot.getCapacity();
        int usedPosition=parkingOrderRepository.findAll().stream().filter(item->item.getParkingLotName()==parkingLotName).collect(Collectors.toList()).size();
        boolean validPositionFlag=(totalCapacity-usedPosition==0)?false:true;
        if(validPositionFlag){
            parkingLot.setCapacity(totalCapacity-usedPosition-1);
            parkingOrderRepository.save(parkingOrder);
            return  "创建订单成功";
        }
        return "没有空余位置，创建订单失败";

    }
}
