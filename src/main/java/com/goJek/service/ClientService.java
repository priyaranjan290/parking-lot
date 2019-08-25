package com.goJek.service;


/**
 * defines the interface which interacts with the system and produces output in desired format
 * */
public interface ClientService {
    void printStatus();

    void leaveSlot(Integer slotNum);

    void parkVehicle(String registrationNumber, String color);

    void createParkingLot(int slots);

    void printRegistrationNumbers(String vehicleColor);

    void printSlotNumbersForColor(String vehicleColor);

    void printSlotNumbersForRegNumber(String registrationNumber);
}
