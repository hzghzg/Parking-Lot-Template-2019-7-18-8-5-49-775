package com.thoughtworks.parking_lot.service;

import com.thoughtworks.parking_lot.entity.ParkingLot;
import com.thoughtworks.parking_lot.repository.ParkingLotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParkingLotService {
    @Autowired
    private ParkingLotRepository parkingLotRepository;

    public List<ParkingLot> findAllParkingLotsByPageSize(int pageNumber, int pageSize) {
        return parkingLotRepository.findAll(PageRequest.of(pageNumber - 1, pageSize)).getContent();
    }
}
