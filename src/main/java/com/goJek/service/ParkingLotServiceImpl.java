package com.goJek.service;


import com.goJek.exception.ParkingException;
import com.goJek.models.ParkingLot;
import com.goJek.models.Slot;
import com.goJek.models.SlotSize;

import java.util.List;

public class ParkingLotServiceImpl implements ParkingLotService {

    private static ParkingLotService parkingLotService;
    private static SlotService slotService = SlotServiceImpl.getInstance();

    public static ParkingLotService getInstance() {
        if (parkingLotService == null) {
            parkingLotService =  new ParkingLotServiceImpl();
        }

        return parkingLotService;
    }

    @Override
    public ParkingLot createParkingLot(int size, SlotSize slotSize) throws ParkingException {
        List<Slot> slots = slotService.createSlots(size, slotSize);
        ParkingLot parkingLot = new ParkingLot(slots);
        return parkingLot;
    }
}
