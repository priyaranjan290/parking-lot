package com.goJek.models;

import java.util.List;

public class ParkingLot {

    private int id;
    private List<Slot> slots;

    private static int nextAvailableId = 1;

    public int getId() {
        return id;
    }

    public List<Slot> getSlots() {
        return slots;
    }

    public ParkingLot(List<Slot> slots) {
        this.id = ParkingLot.nextAvailableId;
        this.slots = slots;

        ParkingLot.nextAvailableId = ParkingLot.nextAvailableId + 1;
    }
}
