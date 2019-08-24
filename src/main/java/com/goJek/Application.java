package com.goJek;

import com.goJek.enums.Commands;
import com.goJek.enums.SlotSize;
import com.goJek.models.*;
import com.goJek.service.ParkingManager;
import com.goJek.service.ParkingManagerImpl;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Application {

    public static void main(String[] args) {

        Scanner scanner = null;
        if (args.length == 1) {
            // file input
            System.out.println("Entering file mode...");
            File file = new File(args[0]);
            try {
                scanner = new Scanner(file);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        } else {

            // command line input
            System.out.println("Entering command line mode...");
            scanner = new Scanner(System.in);
        }


        if (scanner != null) {
            readAndProcessInput(scanner);
        }

    }

    private static void readAndProcessInput(Scanner scanner) {

        ParkingManager parkingManager = ParkingManagerImpl.getInstance();

        while (scanner.hasNextLine()) {
            String command = scanner.next();

            Commands currentCommand = Commands.valueOf(command);

            try {
                switch (currentCommand) {

                    case create_parking_lot:
                        Integer param = scanner.nextInt();
                        ParkingLot parkingLot = parkingManager.createParkingLot(param.intValue(), SlotSize.DEFAULT);
                        System.out.format("Created a parking lot with %d slots\n", parkingLot.getSlots().size());
                        break;

                    case park:
                        String registrationNumber = scanner.next();
                        String color = scanner.next();
                        Vehicle vehicle = new Car(registrationNumber, color);
                        Ticket ticket = parkingManager.parkVehicle(vehicle);
                        if (ticket == null) {
                            System.out.println("Sorry, parking lot is full");
                        } else {
                            System.out.format("Allocated slot number: %d\n", ticket.getSlotId());
                        }
                        break;

                    case leave:
                        Integer slotNum = scanner.nextInt();
                        parkingManager.unparkVehicle(slotNum);
                        System.out.format("Slot number %d is free\n", slotNum);
                        break;

                    case status:
                        break;

                    case registration_numbers_for_cars_with_colour:
                        break;

                    case slot_numbers_for_cars_with_colour:
                        break;

                    case slot_number_for_registration_number:
                        break;

                    case exit:
                        System.out.println("Exit command found... exiting!");
                        break;

                    default:
                        throw new Exception("Unsupported Command!!");

                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

}
