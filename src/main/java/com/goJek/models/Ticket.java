package com.goJek.models;

public class Ticket {

    private int id;
    private long startTimestamp;
    private long endTimestamp;
    private Slot slot;
    private int parkingLotId;

    private static int nextAvailableId = 1;

    public Ticket(Slot nextAvailableSlot, int parkingLotId) {
        this.id = nextAvailableId;
        this.startTimestamp = System.currentTimeMillis();
        this.slot = nextAvailableSlot;
        this.parkingLotId = parkingLotId;

        nextAvailableId = nextAvailableId + 1;
    }

    public int getId() {
        return id;
    }

    public Slot getSlot() {
        return slot;
    }

    public long getStartTimestamp() {
        return startTimestamp;
    }

    public long getEndTimestamp() {
        return endTimestamp;
    }
}
