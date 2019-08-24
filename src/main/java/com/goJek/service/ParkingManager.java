package com.goJek.service;

import com.goJek.enums.SlotSize;
import com.goJek.exception.ParkingException;
import com.goJek.models.*;

public interface ParkingManager {

    ParkingLot createParkingLot(int slots, SlotSize aDefault) throws ParkingException;

    Ticket parkVehicle(Vehicle vehicle) throws ParkingException;
}
