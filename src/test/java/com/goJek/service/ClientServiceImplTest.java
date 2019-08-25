package com.goJek.service;

import org.junit.Test;

import static org.junit.Assert.*;

public class ClientServiceImplTest {

    ClientService clientService = ClientServiceImpl.getInstance();

    @Test
    public void printStatus()  {

    }

    @Test
    public void leaveSlot() {

    }

    @Test
    public void parkVehicle()  {
    }

    @Test
    public void createParkingLot()  {
        clientService.createParkingLot(5);
    }

    @Test
    public void printRegistrationNumbers() {
    }

    @Test
    public void printSlotNumbersForColor() {
    }

    @Test
    public void printSlotNumbersForRegNumber() {
    }

}