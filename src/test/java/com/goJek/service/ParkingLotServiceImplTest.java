package com.goJek.service;

import com.goJek.enums.SlotSize;
import com.goJek.exception.ParkingException;
import com.goJek.models.ParkingLot;
import org.junit.Assert;
import org.junit.Test;


public class ParkingLotServiceImplTest {

    ParkingLotService parkingLotService = ParkingLotServiceImpl.getInstance();

    @Test
    public void createParkingLot() {
        try {
            ParkingLot parkingLot = parkingLotService.createParkingLot(5, SlotSize.DEFAULT);
            Assert.assertNotNull("Parking lot is null!!", parkingLot);
            Assert.assertEquals("slot size not as expected", 5, parkingLot == null ? 0 : parkingLot.getSlots().size());
        } catch (ParkingException e) {
            Assert.assertFalse("parking lot not created!", false);
        }
    }

}