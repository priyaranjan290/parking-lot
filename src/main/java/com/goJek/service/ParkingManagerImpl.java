package com.goJek.service;

import com.goJek.enums.SlotSize;
import com.goJek.exception.ParkingException;
import com.goJek.models.*;

public class ParkingManagerImpl implements ParkingManager {


    private static ParkingManager parkingManager;

    private ParkingLotService parkingLotService = ParkingLotServiceImpl.getInstance();
    private TicketService ticketService = TicketServiceImpl.getInstance();

    private ParkingLot parkingLot;


    public static ParkingManager getInstance() {
        if (parkingManager == null) {
            parkingManager =  new ParkingManagerImpl();
        }

        return parkingManager;
    }

    @Override
    public ParkingLot createParkingLot(int slots, SlotSize slotSize) throws ParkingException {
        parkingLot = parkingLotService.createParkingLot(slots, slotSize);
        return parkingLot;
    }

    @Override
    public Ticket parkVehicle(Vehicle vehicle) throws ParkingException {

        ParkingLot parkingLot = getParkingLot();

        if (parkingLot.isFull()) {
            return null;
        }

        Slot slot = parkingLot.parkVehicle(vehicle);

        if (slot == null) { return null; }

        return ticketService.createTicket(vehicle.getRegistrationNumber(), slot.getId(), parkingLot.getId());

    }

    @Override
    public boolean unparkVehicle(int slotNum) throws ParkingException {
        boolean isVehicleUnparked = parkingLot.unPark(slotNum);
        if (isVehicleUnparked) {
            ticketService.closeTicket(slotNum, parkingLot.getId());
        }

        return isVehicleUnparked;
    }


    public ParkingLot getParkingLot() throws ParkingException {

        if (parkingLot == null) {
            throw new ParkingException("Parking lot is null!");
        }

        return parkingLot;
    }
}
