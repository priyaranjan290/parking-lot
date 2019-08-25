package com.goJek;

import com.goJek.enums.Commands;
import com.goJek.service.ClientService;
import com.goJek.service.ClientServiceImpl;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Application {

    public static void main(String[] args) {

        Scanner scanner = null;
        if (args.length == 1) {
            // file input
            File file = new File(args[0]);
            try {
                scanner = new Scanner(file);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            // command line input
            scanner = new Scanner(System.in);
        }


        if (scanner != null) {
            readAndProcessInput(scanner);
        }

    }

    private static void readAndProcessInput(Scanner scanner) {

        boolean exit = false;
        ClientService clientService = ClientServiceImpl.getInstance();

        while (scanner.hasNextLine() && !exit) {
            String command = scanner.next();

            Commands currentCommand = Commands.valueOf(command);

            try {
                switch (currentCommand) {

                    case create_parking_lot:
                        Integer param = scanner.nextInt();
                        clientService.createParkingLot(param);
                        break;

                    case park:
                        String registrationNumber = scanner.next();
                        String color = scanner.next();
                        clientService.parkVehicle(registrationNumber, color);
                        break;

                    case leave:
                        Integer slotNum = scanner.nextInt();
                        clientService.leaveSlot(slotNum);
                        break;

                    case status:
                        clientService.printStatus();
                        break;

                    case registration_numbers_for_cars_with_colour:
                        clientService.printRegistrationNumbers(scanner.next());
                        break;

                    case slot_numbers_for_cars_with_colour:
                        clientService.printSlotNumbersForColor(scanner.next());
                        break;

                    case slot_number_for_registration_number:
                        clientService.printSlotNumbersForRegNumber(scanner.next());
                        break;

                    case exit:
                        exit = true;
                        break;

                    default:
                        throw new Exception("Unsupported Command!!");

                }
            } catch (Exception e) {

            }

        }
    }

}
