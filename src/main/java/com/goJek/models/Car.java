package com.goJek.models;

import com.goJek.enums.VehicleType;
import com.goJek.exception.ParkingException;

/**
 *
 * This class denotes the "CAR" vehicle type. It extends abstract class vehicle
 *
 * */
public class Car extends Vehicle {

    // constructor
    public Car(String registrationNumber, String color) throws ParkingException {
        super(registrationNumber, VehicleType.CAR, color);
    }
}
