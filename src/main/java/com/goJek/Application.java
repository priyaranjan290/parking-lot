package com.goJek;

import com.goJek.models.ParkingLot;
import com.goJek.models.SlotSize;
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
        } else {
            System.out.println("");
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
                        System.out.format("Created a parking lot with %d slots", parkingLot.getSlots().size());
                        break;

                    case park:
                        String registrationNumber = scanner.next();
                        String color = scanner.next();
                        break;

                    case leave:
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
