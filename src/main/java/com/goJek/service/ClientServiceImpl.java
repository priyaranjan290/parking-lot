package com.goJek.service;

import com.goJek.enums.SlotSize;
import com.goJek.exception.ParkingException;
import com.goJek.models.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ClientServiceImpl implements ClientService {

    private static ClientService clientService;

    private ClientServiceImpl() {}

    private ParkingManager parkingManager = ParkingManagerImpl.getInstance();

    public static ClientService getInstance() {
        if (clientService == null) {
            clientService = new ClientServiceImpl();
        }

        return clientService;
    }


    /**
     * Various Response Formats as Needed by the client
     * */
    public static final String PARKING_LOT_CREATION_RESPONSE = "Created a parking lot with %s slots";
    public static final String PARK_VEHICLE_RESPONSE = "Allocated slot number: %s";
    public static final String PARKING_LOT_FULL_RESPONSE =  "Sorry, parking lot is full";
    public static final String LEAVE_SLOT_RESPOSNE = "Slot number %s is free";
    public static final String PARKING_LOT_STATUS_HEADER = "Slot No.    Registration No    Colour";
    public static final String NOT_FOUND = "Not found";


    /**
     * Fetch slots and current status of the parking lot.
     * Skips slots info which are unoccupied
     *
     * @return return list of formatted strings
     * */
    @Override
    public List<String> getData() {
        List<String> retVal = new ArrayList<>();
        try {
            List<String> data = new ArrayList<>();
            ParkingLot parkingLot = parkingManager.getParkingLot();
            for (Slot slot : parkingLot.getSlots()) {
                if (slot.getVehicle() == null) {continue;}
                String format = String.format("%-11d %-18s %s",
                        slot.getId(),
                        slot.getVehicle() == null ? null : slot.getVehicle().getRegistrationNumber(),
                        slot.getVehicle() == null ? null : slot.getVehicle().getColor());

                data.add(format);
            }
            retVal.add(PARKING_LOT_STATUS_HEADER);
            retVal.addAll(data);

        } catch (ParkingException e) {
            retVal.add(e.getMessage());
        }
        return retVal;
    }


    /**
     * invokes unpark vehicle from parking manager.
     *
     * @return return list of formatted strings
     * */
    @Override
    public List<String> leaveSlot(Integer slotNum) {
        List<String> retVal = new ArrayList<>();
        try {
            if (parkingManager.unparkVehicle(slotNum)) {
                String format = String.format(LEAVE_SLOT_RESPOSNE, slotNum);
                retVal.add(format);
            }
        } catch (ParkingException e) {
            retVal.add(e.getMessage());
        }
        return retVal;
    }

    /**
     * invokes parkVehicle from parking manager.
     *
     * @return return list of formatted strings
     * */
    @Override
    public List<String> parkVehicle(String registrationNumber, String color) {
        List<String> retVal = new ArrayList<>();
        try {
            Vehicle vehicle = new Car(registrationNumber, color);
            Ticket ticket = parkingManager.parkVehicle(vehicle);

            if (ticket == null) {
                retVal.add(PARKING_LOT_FULL_RESPONSE);
            } else {
                retVal.add(String.format(PARK_VEHICLE_RESPONSE, ticket.getSlotId()));
            }
        } catch (ParkingException e) {
            retVal.add(e.getMessage());
        }
        return retVal;
    }


    /**
     * invokes createParkingLot from parking manager.
     *
     * @return return list of formatted strings
     * */
    @Override
    public List<String> createParkingLot(int slots) {
        List<String> retVal = new ArrayList<>();
        ParkingLot parkingLot = null;
        try {
            parkingLot = parkingManager.createParkingLot(slots, SlotSize.DEFAULT);
            String format = String.format(PARKING_LOT_CREATION_RESPONSE, parkingLot.getSlots().size());
            retVal.add(format);
        } catch (ParkingException e) {
            retVal.add(e.getMessage());
        }
        return retVal;
    }


    /**
     *
     * Fetch all slots, filters result
     *  @param vehicleColor -- color to be searched for
     *  @return : list of strings with comma separated registration number of vehicles of the given color
     *
     * */
    @Override
    public List<String> getFormattedRegNumForCarsWithColor(String vehicleColor) {
        List<String> retVal = new ArrayList<>();

        List<Slot> slots = getSlots();
        if (slots == null) {
            retVal.add(NOT_FOUND);
            return retVal;
        }

        String fromatedRegString = slots.stream()
                .filter(x -> x.getVehicle() != null && x.getVehicle().getColor().equals(vehicleColor))
                .map(y -> y.getVehicle().getRegistrationNumber())
                .collect(Collectors.joining(", "));

        if (fromatedRegString == null || "".equals(fromatedRegString)) {
            retVal.add(NOT_FOUND);
        } else {
            retVal.add(fromatedRegString);
        }

        return retVal;
    }


    /**
     *
     * Fetch result,
     *  @param vehicleColor -- color to be searched for
     *  @return return list of formatted strings [comma separated slot number of vehicles of the given color]
     * */
    @Override
    public List<String> getFormattedSlotNumbersWithColor(String vehicleColor) {
        List<String> retVal = new ArrayList<>();

        List<Slot> slots = getSlots();
        if (slots == null) {
            retVal.add(NOT_FOUND);
            return retVal;
        }

        String collect = slots.stream()
                .filter(x -> x.getVehicle() != null && x.getVehicle().getColor().equals(vehicleColor))
                .map(y -> String.valueOf(y.getId()))
                .collect(Collectors.joining(", "));

        if (collect == null || "".equals(collect)) {
            retVal.add(NOT_FOUND);
        } else {
            retVal.add(collect);
        }

        return retVal;
    }


    /**
     * Searches for the desired slot in the given list of slots for a particular vehicle reg number
     *
     *  @param registrationNumber -- registration number of the vehicle to be searched for
     *  @return : list of formatted string containing output
     * */
    @Override
    public List<String> getSlotNumberForRegNumber(String registrationNumber) {
        List<String> retVal = new ArrayList<>();

        List<Slot> slots = getSlots();
        if (slots == null) {
            retVal.add(NOT_FOUND);
            return retVal;
        }

        String collect = slots.stream()
                .filter(x -> x.getVehicle() != null && x.getVehicle().getRegistrationNumber().equals(registrationNumber))
                .map(y -> String.valueOf(y.getId()))
                .collect(Collectors.joining(", "));

        if (collect == null || "".equals(collect)) {
            retVal.add(NOT_FOUND);
        } else {
            retVal.add(collect);
        }
        return retVal;
    }


    /**
     * @return List of Slot in the present parking lot
     * */
    private List<Slot> getSlots() {
        try {
            ParkingLot parkingLot = parkingManager.getParkingLot();
            return parkingLot.getSlots();
        } catch (ParkingException e) {

        }
        return null;
    }


    /**
     * prints the data present in the param
     * @param dataList
     */
    public void printData(List<String> dataList) {
        for (String data : dataList) {
            System.out.println(data);
        }
    }
}
