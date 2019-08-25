package com.goJek.service;

import com.goJek.enums.SlotSize;
import com.goJek.exception.ParkingException;
import com.goJek.models.*;

import java.util.List;

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
    private static final String DUPLICATE_VEHICLE_NOT_ALLOWED = "Duplicate Registration number vehicles are not allowed! ";

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

        // check if vehicle is already parked
        if (isDuplicateVehicle(vehicle.getRegistrationNumber(), parkingLot.getSlots())) {
            throw new ParkingException(DUPLICATE_VEHICLE_NOT_ALLOWED);
        }


        // cannot park if parking lot is full
        if (parkingLot.isFull()) {
            return null;
        }

        // try to park vehicle
        Slot slot = parkingLot.parkVehicle(vehicle);

        if (slot == null) { return null; }

        // issue a ticket to client
        return ticketService.createTicket(vehicle.getRegistrationNumber(), slot.getId(), parkingLot.getId());

    }

    /**
     *
     * Iterates over all the slots to find if the vehicle of given registration number is already parked or not
     *
     * @param registrationNumber : registration number of the vehicle to be searched for
     * @param slots              : list of slots to be searched
     * @return                   : true if found else false
     */
    private boolean isDuplicateVehicle(String registrationNumber, List<Slot> slots) {
        Slot duplicateSlot
                = slots.stream()
                        .filter(slot -> slot.isSlotOccupied() && slot.getVehicle().getRegistrationNumber().equals(registrationNumber))
                        .findAny()
                        .orElse(null);


        return duplicateSlot != null;
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
