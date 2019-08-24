package com.goJek.models;

import com.goJek.exception.ParkingException;

public class Car extends Vehicle {

    public Car(String registrationNumber, String color) throws ParkingException {
        super(registrationNumber, VehicleType.CAR, color);
    }
}
