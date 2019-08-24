package com.goJek.service;

import com.goJek.enums.SlotSize;
import com.goJek.exception.ParkingException;
import com.goJek.models.*;

public interface ParkingManager {

    ParkingLot createParkingLot(int slots, SlotSize slotSize) throws ParkingException;

    Ticket parkVehicle(Vehicle vehicle) throws ParkingException;

    void unparkVehicle(int slotNum);

    ParkingLot getParkingLot() throws ParkingException;

}
