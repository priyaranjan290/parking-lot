package com.goJek.service;


import java.util.List;

/**
 * defines the interface which interacts with the system and produces output in desired format
 * */
public interface ClientService {
    List<String> getData();

    List<String> leaveSlot(Integer slotNum);

    List<String> parkVehicle(String registrationNumber, String color);

    List<String> createParkingLot(int slots);

    List<String> getFormattedRegNumForCarsWithColor(String vehicleColor);

    List<String> getFormattedSlotNumbersWithColor(String vehicleColor);

    List<String> getSlotNumberForRegNumber(String registrationNumber);

    void printData(List<String> dataList);
}
