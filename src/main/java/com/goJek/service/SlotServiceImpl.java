package com.goJek.service;

import com.goJek.exception.ParkingException;
import com.goJek.models.Slot;
import com.goJek.enums.SlotSize;

import java.util.ArrayList;
import java.util.List;

public class SlotServiceImpl implements SlotService {

    private static SlotService slotService;

    private static final String INVALID_SLOT_CAPACITY = "Invalid slot capacity supplied!";

    public static SlotService getInstance() {
        if (slotService == null) {
            slotService = new SlotServiceImpl();
        }

        return slotService;
    }

    @Override
    public List<Slot> createSlots(int size, SlotSize slotSize) throws ParkingException {
        if (size < 0) {
            throw new ParkingException(INVALID_SLOT_CAPACITY);
        }

        List<Slot> retVal = new ArrayList<>();

        for (int i=1; i<=size; i++) {
            retVal.add(new Slot(i, slotSize));
        }

        return retVal;
    }
}
