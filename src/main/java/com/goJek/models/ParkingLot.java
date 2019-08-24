package com.goJek.models;

import java.util.List;

public class ParkingLot {

    private int id;
    private List<Slot> slots;

    private int currOccupiedSlotIndex;

    private static int nextParkingLotId = 1;

    public ParkingLot(List<Slot> slots) {
        this.id = ParkingLot.nextParkingLotId;
        this.slots = slots;
        this.currOccupiedSlotIndex = -1;

        ParkingLot.nextParkingLotId = ParkingLot.nextParkingLotId + 1;
    }


    public int getId() {
        return id;
    }

    public List<Slot> getSlots() {
        return slots;
    }

    private Slot getNextAvailableSlot() {
        if (currOccupiedSlotIndex + 1 < slots.size()) {
            return slots.get(currOccupiedSlotIndex + 1);
        }

        return null;
    }

    public boolean isFull() {

        if (getNextAvailableSlot() == null) {
            return true;
        }

        return false;
    }


    public Slot parkVehicle(Vehicle vehicle) {
        Slot slot = getNextAvailableSlot();
        slot.allocateVehicle(vehicle);

        currOccupiedSlotIndex = currOccupiedSlotIndex + 1;
        return slot;
    }
}
