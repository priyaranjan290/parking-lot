package com.goJek;

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
            readAndPrintInput(scanner);
        } else {
            System.out.println("");
        }

    }

    private static void readAndPrintInput(Scanner scanner) {
        while (scanner.hasNextLine()) {
            String command = scanner.nextLine();
            System.out.println(command);

            if ("exit".equals(command)) {
                System.out.println("Exit command found... exiting!");
                break;
            }
        }
    }

}
