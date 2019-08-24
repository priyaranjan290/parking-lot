package com.goJek.models;

public class Ticket {

    private int id;
    private int slotId;
    private int parkingLotId;
    private String vehicleRegistrationNumber;
    private long startTimestamp;
    private long endTimestamp;

    private static int nextAvailableId = 1;

    public Ticket(String vehicleRegistrationNumber, int slotId,  int parkingLotId) {
        this.id = nextAvailableId;
        this.startTimestamp = System.currentTimeMillis();
        this.endTimestamp = -1l;
        this.slotId = slotId;
        this.parkingLotId = parkingLotId;
        this.vehicleRegistrationNumber = vehicleRegistrationNumber;

        nextAvailableId = nextAvailableId + 1;
    }

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

    public void closeTicket() {
        this.endTimestamp = System.currentTimeMillis();
    }
}
