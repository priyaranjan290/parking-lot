package com.goJek.models;

import com.goJek.enums.SlotSize;

public class Slot {

    private int id;
    private Vehicle vehicle;
    private SlotSize slotSize;

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

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public void allocateVehicle(Vehicle vehicle) { this.vehicle = vehicle; }

    public void deAllocateVehicle() { this.vehicle = null; }
}
