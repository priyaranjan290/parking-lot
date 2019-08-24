package com.goJek.service;

import com.goJek.exception.ParkingException;
import com.goJek.models.ParkingLot;
import com.goJek.models.SlotSize;

public interface ParkingManager {

    ParkingLot createParkingLot(int slots, SlotSize aDefault) throws ParkingException;
}
