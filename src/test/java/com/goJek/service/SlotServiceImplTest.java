package com.goJek.service;

import com.goJek.enums.SlotSize;
import com.goJek.exception.ParkingException;
import com.goJek.models.Slot;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;


public class SlotServiceImplTest {

    SlotService slotService = SlotServiceImpl.getInstance();

    @Test
    public void createSlots()  {

        try {
            slotService.createSlots(-1, SlotSize.DEFAULT);
        } catch (ParkingException e) {
            Assert.assertTrue("slot cannot be created with -1 capacity", true);
        }

        try {
            List<Slot> slots = slotService.createSlots(4, SlotSize.DEFAULT);
            Assert.assertEquals("Slots not created as expected", 4, slots == null ? 0 : slots.size());
        } catch (ParkingException e) {
            Assert.assertFalse("unable to create slots", false);
        }
    }

}