package com.goJek.models;

import com.goJek.enums.SlotSize;
import com.goJek.exception.ParkingException;

public class Slot {

    private int id;
    private Vehicle vehicle;
    private SlotSize slotSize;

    private static final String SLOT_IS_ALREADY_EMPTY = "Slot is already Empty!";

    public Slot(int slotNumber, SlotSize slotSize) {
        this.id  = slotNumber;
        this.slotSize = slotSize == null ? SlotSize.DEFAULT : slotSize;
    }


    public int getId() {
        return id;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public SlotSize getSlotSize() {
        return slotSize;
    }

    public boolean isSlotOccupied() {
        return this.vehicle != null;
    }


    public void allocateVehicle(Vehicle vehicle) { this.vehicle = vehicle; }

    public void deAllocateVehicle() throws ParkingException {
        if (!this.isSlotOccupied()) {
            throw new ParkingException(SLOT_IS_ALREADY_EMPTY);
        }
        this.vehicle = null;
    }
}
