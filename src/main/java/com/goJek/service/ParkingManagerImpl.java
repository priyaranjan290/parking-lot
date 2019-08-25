package com.goJek.service;

import com.goJek.enums.SlotSize;
import com.goJek.exception.ParkingException;
import com.goJek.models.*;

public class ParkingManagerImpl implements ParkingManager {


    private static ParkingManager parkingManager;

    private ParkingLotService parkingLotService = ParkingLotServiceImpl.getInstance();
    private TicketService ticketService = TicketServiceImpl.getInstance();

    /**
     *  Holds the parking lot data
     */
    private ParkingLot parkingLot;

    private ParkingManagerImpl() {}

    public static ParkingManager getInstance() {
        if (parkingManager == null) {
            parkingManager =  new ParkingManagerImpl();
        }

        return parkingManager;
    }


    // error message to be thrown if parking lot object is null
    private static final String PARKING_LOT_IS_NULL = "Parking lot is null!";

    /**
     *
     * Creates a new parking lot with the specified params
     *
     * @param slots         : capacity of the parking lot
     * @param slotSize      : type of slots
     * @return              : ParkingLot object created
     * @throws ParkingException
     */
    @Override
    public ParkingLot createParkingLot(int slots, SlotSize slotSize) throws ParkingException {
        parkingLot = parkingLotService.createParkingLot(slots, slotSize);
        return parkingLot;
    }

    /**
     *
     * Parks a vehicle in the parking lot and generates a new ticket for the client
     *
     * @param vehicle       : vehicle to be parked
     * @return              : Ticket object created
     * @throws ParkingException
     */
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

    /**
     *
     * Unparks the vehicle from the given slot, Also asks ticket Service to close the corresponding ticket
     *
     * @param slotNum       : slot number to be freed
     * @return              : boolean
     * @throws ParkingException
     */
    @Override
    public boolean unparkVehicle(int slotNum) throws ParkingException {
        boolean isVehicleUnparked = parkingLot.unPark(slotNum);
        if (isVehicleUnparked) {
            ticketService.closeTicket(slotNum, parkingLot.getId());
        }

        return isVehicleUnparked;
    }


    /**
     *
     * Returns a parking lot, to be used for operations
     *
     * @return                      : ParkingLot object
     * @throws ParkingException
     */
    public ParkingLot getParkingLot() throws ParkingException {

        if (parkingLot == null) {
            throw new ParkingException(PARKING_LOT_IS_NULL);
        }

        return parkingLot;
    }
}
