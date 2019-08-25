package com.goJek.service;

import com.goJek.exception.ParkingException;
import com.goJek.models.Slot;
import com.goJek.enums.SlotSize;

import java.util.ArrayList;
import java.util.List;

public class SlotServiceImpl implements SlotService {

    private static SlotService slotService;

    private SlotServiceImpl() {}

    // error message to throw in case invalid slot capacity is specified while creation
    private static final String INVALID_SLOT_CAPACITY = "Invalid slot capacity supplied!";

    public static SlotService getInstance() {
        if (slotService == null) {
            slotService = new SlotServiceImpl();
        }

        return slotService;
    }

    /**
     *
     * create a list of slots for the parking lot
     *
     * @param size          : capacity
     * @param slotSize      : type of slots to be created
     * @return
     * @throws ParkingException
     */
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
