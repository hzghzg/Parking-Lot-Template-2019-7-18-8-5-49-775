package com.thoughtworks.parking_lot.controller;

import com.thoughtworks.parking_lot.entity.ParkingLot;
import com.thoughtworks.parking_lot.entity.ParkingOrder;
import com.thoughtworks.parking_lot.repository.ParkingLotRepository;
import com.thoughtworks.parking_lot.repository.ParkingOrderRepository;
import com.thoughtworks.parking_lot.service.ParkingOrderService;
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
    @Autowired
    private ParkingOrderService parkingOrderService;
    @PostMapping
    public String addOrders(@RequestBody ParkingOrder parkingOrder){
        boolean validPositionFlag=parkingOrderService.isHasEmptyPosition(parkingOrder);
        if(validPositionFlag){
            parkingOrderRepository.save(parkingOrder);
            return  "创建订单成功";
        }
        return "没有空余位置，创建订单失败";
    }
    @PutMapping
    public List<ParkingOrder> updateOrders(@RequestBody ParkingOrder parkingOrder){
        int orderParkingLotExistFlag=parkingOrderService.isHasParkingLotInOrder(parkingOrder);//是否有相应停车场名字的订单
        if(orderParkingLotExistFlag>0){
            int orderLicenseExistFlag=parkingOrderService.isLicenseNumberExistOrder(parkingOrder);//是否有相应车牌号的车
            int staus=parkingOrderService.isStatusOnInOrder(parkingOrder);//订单状态是否是"off"
            return parkingOrderService.update(parkingOrder,orderLicenseExistFlag,staus);
        }
        return null;
    }
}
