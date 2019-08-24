package com.goJek.service;

import com.goJek.exception.ParkingException;
import com.goJek.models.Slot;
import com.goJek.enums.SlotSize;

import java.util.ArrayList;
import java.util.List;

public class SlotServiceImpl implements SlotService {

    private static SlotService slotService;

    public static SlotService getInstance() {
        if (slotService == null) {
            slotService = new SlotServiceImpl();
        }

        return slotService;
    }

    @Override
    public List<Slot> createSlots(int size, SlotSize slotSize) throws ParkingException {
        if (size < 0) {
            throw new ParkingException("invalid slot size!");
        }

        List<Slot> retVal = new ArrayList<>();

        for (int i=1; i<=size; i++) {
            retVal.add(new Slot(i, slotSize));
        }

        return retVal;
    }
}
