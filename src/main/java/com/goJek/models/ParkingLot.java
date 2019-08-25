package com.goJek.models;

import com.goJek.exception.ParkingException;

import java.util.List;

/**
 * This class stores information regarding the parking lot itself
 * */
public class ParkingLot {

    // unique identofier
    private int id;

    // stores ordered list of slots ordered from 1 to N
    private List<Slot> slots;


    // unique id generator
    private static int nextParkingLotId = 1;

    // constructor
    public ParkingLot(List<Slot> slots) {
        this.id = ParkingLot.nextParkingLotId;
        this.slots = slots;

        ParkingLot.nextParkingLotId = ParkingLot.nextParkingLotId + 1;
    }


    /**
     * Getter methods for class fields
     * */
    public int getId() {
        return id;
    }

    public List<Slot> getSlots() {
        return slots;
    }


    /**
     * Iterates through the ordered list of slots in parking lot to find an empty slot.
     * Currently, it finds the nearest available slot from the entry point.
     *
     * @return Slot : returns a suitable slot if found
     * */
    private Slot getNextAvailableSlot() {
        for (Slot slot : slots) {
            if (!slot.isSlotOccupied()) {
                return slot;
            }
        }

        return null;
    }


    /**
     * checks if the parking lot is full or not
     *
     * @return boolean : true if parking lot is full false o/w
     * */
    public boolean isFull() {

        if (getNextAvailableSlot() == null) {
            return true;
        }

        return false;
    }

    /**
     *
     * Finds next available slot. Allocates the vehicle to the found slot
     *
     * @param vehicle   -- vehicle to be parked
     * @return Slot :   -- slot where vehicle is parked, null if no suitable slot is found
     * */
    public Slot parkVehicle(Vehicle vehicle) {
        Slot slot = getNextAvailableSlot();

        if (slot == null) {
            return null;
        }

        slot.allocateVehicle(vehicle);
        return slot;
    }


    /**
     *
     * Free a particular slot.
     *
     * @param slotNum  : slot number to be freed
     * @return boolean : true if success else false
     *
     * */
    public boolean unPark(int slotNum) throws ParkingException {

        if (slotNum > getSlots().size() || slotNum < 1) {
            return false;
        }

        Slot slot = getSlots().get(slotNum - 1);
        slot.deAllocateVehicle();
        return true;
    }
}
