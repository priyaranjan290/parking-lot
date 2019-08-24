package com.goJek.service;

import com.goJek.enums.SlotSize;
import com.goJek.exception.ParkingException;
import com.goJek.models.*;

import java.util.List;
import java.util.stream.Collectors;

public class ClientServiceImpl implements ClientService {

    private static ClientService clientService;

    private ParkingManager parkingManager = ParkingManagerImpl.getInstance();

    public static ClientService getInstance() {
        if (clientService == null) {
            clientService = new ClientServiceImpl();
        }

        return clientService;
    }


    public static final String PARKING_LOT_CREATION_RESPONSE = "Created a parking lot with %s slots";
    public static final String PARK_VEHICLE_RESPONSE = "Allocated slot number: %s";
    public static final String PARKING_LOT_FULL_RESPONSE =  "Sorry, parking lot is full";
    public static final String LEAVE_SLOT_RESPOSNE = "Slot number %s is free";
    public static final String PARKING_LOT_STATUS_HEADER = "Slot No.    Registration No    Colour";
    public static final String NOT_FOUND = "Not found";


    @Override
    public void printStatus() {
        try {
            ParkingLot parkingLot = parkingManager.getParkingLot();

            System.out.println(PARKING_LOT_STATUS_HEADER);

            for (Slot slot : parkingLot.getSlots()) {
                String format = String.format("%-11d %-18s %s",
                        slot.getId(),
                        slot.getVehicle() == null ? null : slot.getVehicle().getRegistrationNumber(),
                        slot.getVehicle() == null ? null : slot.getVehicle().getColor()
                );


                System.out.println(format);
            }
        } catch (ParkingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void leaveSlot(Integer slotNum) {
        parkingManager.unparkVehicle(slotNum);
        String format = String.format(LEAVE_SLOT_RESPOSNE, slotNum);
        System.out.println(format);
    }

    @Override
    public void parkVehicle(String registrationNumber, String color) {
        try {

            Vehicle vehicle = new Car(registrationNumber, color);
            Ticket ticket = parkingManager.parkVehicle(vehicle);

            if (ticket == null) {
                System.out.println(PARKING_LOT_FULL_RESPONSE);
            } else {
                System.out.println(String.format(PARK_VEHICLE_RESPONSE, ticket.getSlotId()));
            }
        } catch (ParkingException e) {

        }
    }

    @Override
    public void createParkingLot(int slots) {
        ParkingLot parkingLot = null;
        try {
            parkingLot = parkingManager.createParkingLot(slots, SlotSize.DEFAULT);
            String format = String.format(PARKING_LOT_CREATION_RESPONSE, parkingLot.getSlots().size());
            System.out.println(format);
        } catch (ParkingException e) {

        }
    }

    @Override
    public void printRegistrationNumbers(String vehicleColor) {
        List<Slot> slots = getSlots();

        if (slots == null) {
            System.out.println(NOT_FOUND);
            return;
        }

        String collect = slots.stream()
                .filter(x -> x.getVehicle() != null && x.getVehicle().getColor().equals(vehicleColor))
                .map(y -> y.getVehicle().getRegistrationNumber())
                .collect(Collectors.joining(", "));

        if (collect == null || "".equals(collect)) {
            System.out.println(NOT_FOUND);
        } else {
            System.out.println(collect);
        }

    }


    @Override
    public void printSlotNumbersForColor(String vehicleColor) {
        List<Slot> slots = getSlots();

        if (slots == null) {
            System.out.println(NOT_FOUND);
            return;
        }

        String collect = slots.stream()
                .filter(x -> x.getVehicle() != null && x.getVehicle().getColor().equals(vehicleColor))
                .map(y -> String.valueOf(y.getId()))
                .collect(Collectors.joining(", "));

        if (collect == null || "".equals(collect)) {
            System.out.println(NOT_FOUND);
        } else {
            System.out.println(collect);
        }

    }

    @Override
    public void printSlotNumbersForRegNumber(String registrationNumber) {
        List<Slot> slots = getSlots();

        if (slots == null) {
            System.out.println(NOT_FOUND);
            return;
        }

        String collect = slots.stream()
                .filter(x -> x.getVehicle() != null && x.getVehicle().getRegistrationNumber().equals(registrationNumber))
                .map(y -> String.valueOf(y.getId()))
                .collect(Collectors.joining(", "));

        if (collect == null || "".equals(collect)) {
            System.out.println(NOT_FOUND);
        } else {
            System.out.println(collect);
        }
    }

    private List<Slot> getSlots() {
        try {
            ParkingLot parkingLot = parkingManager.getParkingLot();
            return parkingLot.getSlots();
        } catch (ParkingException e) {

        }
        return null;
    }
}
