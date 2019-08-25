package com.goJek.models;

import com.goJek.enums.SlotSize;
import com.goJek.exception.ParkingException;

/**
 * This class models the slot that is used to park/unpark the vehicle
 * */
public class Slot {

    // unique identifier
    private int id;

    // vehicle in the given slot
    private Vehicle vehicle;

    // type of slot (can be used for deciding slot of different type of vehicles)
    private SlotSize slotSize;


    // error message to show when trying to vacate an empty slot
    private static final String SLOT_IS_ALREADY_EMPTY = "Slot is already Empty!";

    // constructor
    public Slot(int slotNumber, SlotSize slotSize) {
        this.id  = slotNumber;
        this.slotSize = slotSize == null ? SlotSize.DEFAULT : slotSize;
    }


    /**
     * Getter methods
     * */
    public int getId() {
        return id;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public SlotSize getSlotSize() {
        return slotSize;
    }

    /**
     * checks of the current slot is occupied or not
     * @return boolean
     * */
    public boolean isSlotOccupied() {
        return this.vehicle != null;
    }

    /**
     * allocates the desired vehicle to the current slot
     * @param vehicle
     * */
    public void allocateVehicle(Vehicle vehicle) { this.vehicle = vehicle; }


    /**
     * removes the vehicle if present in the current slot -- frees the slot
     * */
    public void deAllocateVehicle() throws ParkingException {
        if (!this.isSlotOccupied()) {
            throw new ParkingException(SLOT_IS_ALREADY_EMPTY);
        }
        this.vehicle = null;
    }
}
