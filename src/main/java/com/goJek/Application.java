package com.goJek;

import com.goJek.enums.Commands;
import com.goJek.exception.ParkingException;
import com.goJek.service.ClientService;
import com.goJek.service.ClientServiceImpl;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Application {

    private static final String UNSUPPORTED_COMMAND = "Unsupported Command!!";


    /**
     * entry point of he code
     * */
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


    /**
     *
     * method to read and parse input and take appropriate action
     *
     * */
    private static void readAndProcessInput(Scanner scanner) {

        boolean exit = false;
        ClientService clientService = ClientServiceImpl.getInstance();

        while (scanner.hasNextLine() && !exit) {
            String command = scanner.next();

            try {

                Commands currentCommand = Commands.valueOf(command.toUpperCase());

                switch (currentCommand) {

                    case CREATE_PARKING_LOT:
                        Integer param = scanner.nextInt();
                        clientService.printData(clientService.createParkingLot(param));
                        break;

                    case PARK:
                        String registrationNumber = scanner.next();
                        String color = scanner.next();
                        clientService.printData(clientService.parkVehicle(registrationNumber, color));
                        break;

                    case LEAVE:
                        Integer slotNum = scanner.nextInt();
                        clientService.printData(clientService.leaveSlot(slotNum));
                        break;

                    case STATUS:
                        clientService.printData(clientService.getData());
                        break;

                    case REGISTRATION_NUMBERS_FOR_CARS_WITH_COLOUR:
                        clientService.printData(clientService.getFormattedRegNumForCarsWithColor(scanner.next()));
                        break;

                    case SLOT_NUMBERS_FOR_CARS_WITH_COLOUR:
                        clientService.printData(clientService.getFormattedSlotNumbersWithColor(scanner.next()));
                        break;

                    case SLOT_NUMBER_FOR_REGISTRATION_NUMBER:
                        clientService.printData(clientService.getSlotNumberForRegNumber(scanner.next()));
                        break;

                    case EXIT:
                        exit = true;
                        break;

                    default:
                        throw new ParkingException(UNSUPPORTED_COMMAND);

                }
            } catch (IllegalArgumentException e) {
                System.out.println(UNSUPPORTED_COMMAND);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

        }
    }

}
