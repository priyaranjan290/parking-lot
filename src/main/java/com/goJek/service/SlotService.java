package com.goJek.service;

import com.goJek.exception.ParkingException;
import com.goJek.models.Slot;
import com.goJek.enums.SlotSize;

import java.util.List;

public interface SlotService {
    List<Slot> createSlots(int size, SlotSize slotSize) throws ParkingException;
}
