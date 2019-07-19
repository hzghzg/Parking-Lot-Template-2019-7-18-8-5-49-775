package com.thoughtworks.parking_lot.service;

import com.thoughtworks.parking_lot.entity.ParkingLot;
import com.thoughtworks.parking_lot.entity.ParkingOrder;
import com.thoughtworks.parking_lot.repository.ParkingLotRepository;
import com.thoughtworks.parking_lot.repository.ParkingOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ParkingOrderService {
    @Autowired
    private ParkingLotRepository parkingLotRepository;
    @Autowired
    private ParkingOrderRepository parkingOrderRepository;
    public boolean isHasEmptyPosition(ParkingOrder parkingOrder){
        String parkingLotName=parkingOrder.getParkingLotName();
        ParkingLot parkingLot=parkingLotRepository.findById(parkingLotName).get();
        int totalCapacity=parkingLot.getCapacity();
        int usedPosition=parkingOrderRepository.findAll().stream().filter(item->item.getParkingLotName()==parkingLotName).collect(Collectors.toList()).size();
        boolean validPositionFlag=(totalCapacity-usedPosition==0)?false:true;
        if(validPositionFlag) parkingLot.setCapacity(totalCapacity-usedPosition-1);
        return  validPositionFlag;
    }

    public int isHasParkingLotInOrder(ParkingOrder parkingOrder){
        return  parkingOrderRepository.findAll().stream().filter(item->item.getParkingLotName()==parkingOrder.getParkingLotName()).collect(Collectors.toList()).size();
    }

    public int isLicenseNumberExistOrder(ParkingOrder parkingOrder){
        return  parkingOrderRepository.findAll().stream().filter(item->item.getParkingLotName()==parkingOrder.getParkingLotName()).collect(Collectors.toList())
                .stream().filter(item1->item1.getLicensePlateNumber()==parkingOrder.getLicensePlateNumber()).collect(Collectors.toList()).size();
    }

    public int isStatusOnInOrder(ParkingOrder parkingOrder){
        return  parkingOrderRepository.findAll().stream().filter(item->item.getParkingLotName()==parkingOrder.getParkingLotName()).collect(Collectors.toList())
                .stream().filter(item1->item1.getLicensePlateNumber()==parkingOrder.getLicensePlateNumber()).collect(Collectors.toList())
                .stream().filter(item2->item2.getOrderStatus()=="on").collect(Collectors.toList()).size();
    }

    public List<ParkingOrder> update(ParkingOrder parkingOrder, int orderLicenseExistFlag, int staus){
        if(orderLicenseExistFlag>0&&staus>0){
            parkingOrder.setOrderStatus("off");
            parkingOrderRepository.save(parkingOrder);
            return  parkingOrderRepository.findAll();
        }
        return null;
    }
}
