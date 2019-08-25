package com.goJek.service;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;


public class ClientServiceImplTest {

    private ClientService clientService = ClientServiceImpl.getInstance();

    @Test
    public void getData() {
        clientService.createParkingLot(3);
        clientService.parkVehicle("REG_1", "W");
        clientService.parkVehicle("REG_2", "W");

        List<String> data = clientService.getData();

        Assert.assertEquals("Slot No.    Registration No    Colour", data.get(0));
        Assert.assertEquals("1           REG_1              W", data.get(1));
        Assert.assertEquals("2           REG_2              W", data.get(2));
    }

    @Test
    public void leaveSlot() {
        clientService.createParkingLot(3);
        clientService.parkVehicle("REG_1", "W");
        clientService.parkVehicle("REG_2", "W");


        List<String> strings = clientService.leaveSlot(2);
        Assert.assertEquals("Slot number 2 is free", strings.get(0));

        strings = clientService.leaveSlot(2);
        Assert.assertEquals("Slot is already Empty!", strings.get(0));
    }

    @Test
    public void parkVehicle() {
        clientService.createParkingLot(1);
        List<String> strings = clientService.parkVehicle("REG_1", "W");
        Assert.assertEquals("Allocated slot number: 1", strings.get(0));

        strings = clientService.parkVehicle("REG_2", "W");
        Assert.assertEquals("Sorry, parking lot is full", strings.get(0));
    }

    @Test
    public void createParkingLot() {
        List<String> retVal = clientService.createParkingLot(7);
        Assert.assertEquals("Created a parking lot with 7 slots", retVal.get(0));
    }

    @Test
    public void getFormattedRegNumForCarsWithColor() {
        clientService.createParkingLot(3);
        clientService.parkVehicle("REG_1", "W");
        clientService.parkVehicle("REG_2", "R");
        clientService.parkVehicle("REG_3", "W");

        List<String> stringList = clientService.getFormattedRegNumForCarsWithColor("W");
        Assert.assertEquals("REG_1, REG_3", stringList.get(0));
    }

    @Test
    public void getFormattedSlotNumbersWithColor() {
        clientService.createParkingLot(3);
        clientService.parkVehicle("REG_1", "W");
        clientService.parkVehicle("REG_2", "R");
        clientService.parkVehicle("REG_3", "W");

        List<String> stringList = clientService.getFormattedSlotNumbersWithColor("W");
        Assert.assertEquals("1, 3", stringList.get(0));
    }

    @Test
    public void getSlotNumberForRegNumber() {
        clientService.createParkingLot(3);
        clientService.parkVehicle("REG_1", "W");
        clientService.parkVehicle("REG_2", "R");
        clientService.parkVehicle("REG_3", "W");

        List<String> stringList = clientService.getSlotNumberForRegNumber("REG_2");
        Assert.assertEquals("2", stringList.get(0));
    }

}