package com.goJek.models;

import com.goJek.enums.VehicleType;
import com.goJek.exception.ParkingException;

/**
 * Base class for Different vehicle types
 * */
public abstract class Vehicle {

    // class fields
    private String registrationNumber;
    private VehicleType vehicleType;
    private String color;


    // Error messages to be thrown
    private static final String REGISTRATION_NUMBER_EMPTY = "Registration Number is Empty!";
    private static final String VEHICLE_TYPE_EMPTY = "Vehicle Type is Empty!";
    private static final String EMPTY_COLOR = "Color of Vehicle is Empty!";


    // constructor
    public Vehicle(String registrationNumber, VehicleType vehicleType, String color) throws ParkingException {

        if (registrationNumber == null || registrationNumber.length() == 0 ) {
            throw new ParkingException(REGISTRATION_NUMBER_EMPTY);
        }

        if (vehicleType == null ) {
            throw new ParkingException(VEHICLE_TYPE_EMPTY);
        }

        if (color == null || color.length() == 0) {
            throw new ParkingException(EMPTY_COLOR);
        }

        this.registrationNumber = registrationNumber;
        this.vehicleType = vehicleType;
        this.color = color;
    }


    /**
     * Getter methods for class members
     * */
    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public String getColor() {
        return color;
    }

}
