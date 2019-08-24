package com.goJek.service;

import com.goJek.exception.ParkingException;
import com.goJek.models.ParkingLot;
import com.goJek.enums.SlotSize;

public interface ParkingLotService {
    ParkingLot createParkingLot(int slots, SlotSize slotSize) throws ParkingException;
}
