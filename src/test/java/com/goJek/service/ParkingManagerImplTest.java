package com.goJek.service;


import com.goJek.enums.SlotSize;
import com.goJek.exception.ParkingException;
import com.goJek.models.*;
import org.junit.Assert;

import org.junit.Test;


public class ParkingManagerImplTest {

    private ParkingManager parkingManager = ParkingManagerImpl.getInstance();
    private ParkingLotServiceImplTest parkingLotServiceImplTest = new ParkingLotServiceImplTest();

    @Test
    public void createParkingLot() {
        parkingLotServiceImplTest.createParkingLot();
    }

    @Test
    public void parkVehicle() {
        try {
            Vehicle vehicle = new Car("REG_1", "W");

            ParkingLot parkingLot = parkingManager.getParkingLot();
            if (parkingLot == null) {
                parkingLot = parkingManager.createParkingLot(5, SlotSize.DEFAULT);
            }

            Slot slot = parkingLot.getSlots().stream().filter(x -> !x.isSlotOccupied()).findFirst().orElse(null);

            Ticket ticket = parkingManager.parkVehicle(vehicle);

            if (slot == null) {
                Assert.assertNull("Ticket is Null", ticket);
            } else {
                Assert.assertNotNull("Ticket is Null", ticket);
                Assert.assertEquals("Slot is not correct", slot.getId(), ticket.getSlotId());
                Assert.assertEquals("Parking Lot id not correct", 1,  ticket.getparkingLotId());
                Assert.assertEquals("Wrong vehicle parked!!", vehicle.getRegistrationNumber(), ticket.getVehicleRegistrationNumber());
            }



        } catch (ParkingException e) {
            Assert.assertFalse("Vehicle could not be parked!", false);
        }
    }

    @Test
    public void unparkVehicle() {

        // create parking lot
        ParkingLot parkingLot = null;
        Vehicle vehicle = null;
        try {
            parkingLot = parkingManager.createParkingLot(10, SlotSize.DEFAULT);
            vehicle = new Car("REG_1", "w");

            parkingManager.parkVehicle(vehicle);
        } catch (ParkingException e) {
            e.printStackTrace();
        }


        // test 1 -> vacate an empty slot
        try {
            parkingManager.unparkVehicle(3);
        } catch (ParkingException e) {
            Assert.assertEquals("Slot is already Empty!", e.getMessage());
        }

        // test 2 -> vacate an invalid slot

        try {
            boolean vacated = parkingManager.unparkVehicle(11);
            Assert.assertFalse("It should not be vacated", vacated);
        } catch (ParkingException e) {

        }

        // test 3 -> vacate an occupied slot
        try {
            boolean vacated = parkingManager.unparkVehicle(1);
            Assert.assertTrue("It should  be vacated", vacated);
        } catch (ParkingException e) {

        }

    }

    @Test
    public void getParkingLot() {
        ParkingLot parkingLot = null;
        try {
            parkingLot = parkingManager.getParkingLot();
            Assert.assertNotNull("Parking lot should be null", parkingLot);
        } catch (ParkingException e) {
            try {
                parkingLot = parkingManager.getParkingLot();
            } catch (ParkingException e1) {
                Assert.assertNull("Parking lot should be null", parkingLot);
            }
        }
    }

}