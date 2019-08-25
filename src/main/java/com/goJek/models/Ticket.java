package com.goJek.models;


/**
 * This class holds the ticket information issued to the client when vehicle is parked
 * */
public class Ticket {

    // unique identifier
    private int id;

    // information about the location where vehicle was parked
    private int slotId;
    private int parkingLotId;

    // vehicle information
    private String vehicleRegistrationNumber;

    // stores time duration for which the vehicle was parked -> can be used for billing during exit
    private long startTimestamp;
    private long endTimestamp;

    // id generator for the class
    private static int nextAvailableId = 1;


    // constructor
    public Ticket(String vehicleRegistrationNumber, int slotId,  int parkingLotId) {
        this.id = nextAvailableId;
        this.startTimestamp = System.currentTimeMillis();
        this.endTimestamp = -1l;
        this.slotId = slotId;
        this.parkingLotId = parkingLotId;
        this.vehicleRegistrationNumber = vehicleRegistrationNumber;

        nextAvailableId = nextAvailableId + 1;
    }

    /**
     * Getter methods for class members
     * */
    public int getId() {
        return id;
    }

    public int getSlotId() {
        return slotId;
    }

    public int getparkingLotId() {
        return parkingLotId;
    }

    public long getStartTimestamp() {
        return startTimestamp;
    }

    public long getEndTimestamp() {
        return endTimestamp;
    }


    /**
     * called when the vehicle is unparked
     * */
    public void closeTicket() {
        this.endTimestamp = System.currentTimeMillis();
    }
}
