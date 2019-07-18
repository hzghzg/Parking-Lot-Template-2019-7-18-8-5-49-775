package com.thoughtworks.parking_lot.repository;

import com.thoughtworks.parking_lot.entity.ParkingLot;
//import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ParkingLotRepositoryTest {
    @Autowired
    ParkingLotRepository parkingLotRepository;
    //Story AC1
    @Test
    public void should_return_true_information_when_addParkingLot_given_some_parkingLot() {
        //given
        ParkingLot parkingLot1=new ParkingLot("parkinglot1",50,1);
        ParkingLot parkingLot2=new ParkingLot("parkinglot2",60,2);
        ParkingLot parkingLot3=new ParkingLot("parkinglot3",55,3);
        ParkingLot parkingLot4=new ParkingLot("parkinglot4",40,4);
        ParkingLot parkingLot5=new ParkingLot("parkinglot5",45,5);
        //when
        parkingLotRepository.save(parkingLot1);
        parkingLotRepository.save(parkingLot2);
        parkingLotRepository.save(parkingLot3);
        parkingLotRepository.save(parkingLot4);
        parkingLotRepository.save(parkingLot5);
        //then
        Assertions.assertEquals(5,parkingLotRepository.findAll().size());
        Assertions.assertEquals("parkinglot1",parkingLotRepository.findAll().get(0).getName());
        Assertions.assertEquals(50,parkingLotRepository.findAll().get(0).getCapacity());
        Assertions.assertEquals(1,parkingLotRepository.findAll().get(0).getAddress());
    }
    //Story AC2
    @Test
    public void should_return_true_total_number_of_parkinglot_when_deleteParkingLot_given_some_parkingLot() {
        //given
        ParkingLot parkingLot1=new ParkingLot("parkinglot1",50,1);
        ParkingLot parkingLot2=new ParkingLot("parkinglot2",60,2);
        ParkingLot parkingLot3=new ParkingLot("parkinglot3",55,3);
        ParkingLot parkingLot4=new ParkingLot("parkinglot4",40,4);
        ParkingLot parkingLot5=new ParkingLot("parkinglot5",45,5);
        ParkingLot deleteParkingLot=parkingLot1;
        //when
        parkingLotRepository.save(parkingLot1);
        parkingLotRepository.save(parkingLot2);
        parkingLotRepository.save(parkingLot3);
        parkingLotRepository.save(parkingLot4);
        parkingLotRepository.save(parkingLot5);
        parkingLotRepository.delete(deleteParkingLot);
        //then
        Assertions.assertEquals(4,parkingLotRepository.findAll().size());
    }

}