package com.goJek.service;

import com.goJek.exception.ParkingException;
import com.goJek.models.ParkingLot;
import com.goJek.models.SlotSize;

public class ParkingManagerImpl implements ParkingManager {


    private static ParkingManager parkingManager;
    private static ParkingLotService parkingLotService = ParkingLotServiceImpl.getInstance();
    private static ParkingLot parkingLot;


    public static ParkingManager getInstance() {
        if (parkingManager == null) {
            parkingManager =  new ParkingManagerImpl();
        }

        return parkingManager;
    }

    @Override
    public ParkingLot createParkingLot(int slots, SlotSize slotSize) throws ParkingException {
        parkingLot = parkingLotService.createParkingLot(slots, slotSize);
        return parkingLot;
    }
}
