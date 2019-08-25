package com.goJek.service;


import com.goJek.exception.ParkingException;
import com.goJek.models.ParkingLot;
import com.goJek.models.Slot;
import com.goJek.enums.SlotSize;

import java.util.List;

public class ParkingLotServiceImpl implements ParkingLotService {

    private static ParkingLotService parkingLotService;

    private ParkingLotServiceImpl() {}

    private SlotService slotService = SlotServiceImpl.getInstance();

    public static ParkingLotService getInstance() {
        if (parkingLotService == null) {
            parkingLotService =  new ParkingLotServiceImpl();
        }

        return parkingLotService;
    }


    /**
     *
     * Creates a new parking lot with the given specifications
     *
     * @param size      : number of slots desired in the parking lot
     * @param slotSize  : enum indicating the slot type
     *
     * @return ParkingLot object created
     * @throws ParkingException
     *
     * */
    @Override
    public ParkingLot createParkingLot(int size, SlotSize slotSize) throws ParkingException {
        List<Slot> slots = slotService.createSlots(size, slotSize);
        ParkingLot parkingLot = new ParkingLot(slots);
        return parkingLot;
    }
}
