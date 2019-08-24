package com.goJek.models;

import java.util.List;

public class ParkingLot {

    private int id;
    private List<Slot> slots;

    private static int nextParkingLotId = 1;

    public ParkingLot(List<Slot> slots) {
        this.id = ParkingLot.nextParkingLotId;
        this.slots = slots;

        ParkingLot.nextParkingLotId = ParkingLot.nextParkingLotId + 1;
    }


    public int getId() {
        return id;
    }

    public List<Slot> getSlots() {
        return slots;
    }

    private Slot getNextAvailableSlot() {
        for (Slot slot : slots) {
            if (!slot.isSlotOccupied()) {
                return slot;
            }
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
        return slot;
    }

    public boolean unPark(Integer slotNum) {
        Slot slot = getSlots().get(slotNum - 1);
        slot.deAllocateVehicle();
        return true;
    }
}
