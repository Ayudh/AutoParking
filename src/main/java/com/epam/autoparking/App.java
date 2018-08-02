package com.epam.autoparking;

import java.time.LocalDateTime;
import java.util.Scanner;

/**
 * App class.
 */
final class App {

  /**
   * private default constructor.
   */
  private App() {

  }

  /**
   * constant for admin choice to park a vehicle.
   */
  private static final int CHOICE_1 = 1;

  /**
   * constant for admin choice to unpark a vehicle.
   */
  private static final int CHOICE_2 = 2;

  /**
   * constant for admin choice to check the status of vehicle.
   */
  private static final int CHOICE_3 = 3;

  /**
   * constant for admin choice to exit the application.
   */
  private static final int CHOICE_4 = 4;

  /**
   * Main method Starting point of execution.
   * @param args command line parameters
   */
  public static void main(final String[] args) {

    Scanner scanner = new Scanner(System.in);

    // Authenticating the admin
    System.out.print("Enter admin username:\t");
    String userName = scanner.next();
    System.out.print("Enter password:\t");
    String password = scanner.next();

    if (!Login.validateUser(userName, password)) {
      System.out.println("Error: Authentication failed. "
          + "Username or Password is incorrect.");
      System.exit(0);
    }

    System.out.println("[INFO]\tAuthenticated");

    int parkingLotSize;
    ParkingLot parkingLot;

    TransactionHandler transactionHandler = TransactionHandler.getInstance();

    if (transactionHandler.isValid()) {

      System.out.println("[INFO]Reading from transaction File");
      DataFormat dataFormat = transactionHandler.readRows();
      parkingLotSize = Integer.parseInt(dataFormat.getRow(0).get(0));
      parkingLot = new ParkingLot(parkingLotSize);
      for (int i = 1; i < dataFormat.noOfRows(); i++) {
        String id = dataFormat.getRow(i).get(0);
        int slotNumber = Integer.parseInt(dataFormat.getRow(i).get(1));
        LocalDateTime inTime = LocalDateTime.parse(dataFormat.getRow(i).get(2));
        System.out.println(id + " " + slotNumber + inTime);
        parkingLot.assignSlot(id, slotNumber, inTime);
      }
    } else {

      // Admin enters the size of parking lot
      System.out.print("Enter the size of parking lot:");
      parkingLotSize = scanner.nextInt();
      parkingLot = new ParkingLot(parkingLotSize);
      transactionHandler.writeSize(parkingLotSize);
    }


    do {
      System.out.println("Enter your choice:");
      System.out.println("1.Park a vehicle\n"
      + "2.Unpark a vehicle\n"
          + "3.Check status of a vehicle\n4.Exit");

      scanner.nextLine();
      int choice = scanner.nextInt();

      switch (choice) {

        case CHOICE_1:
          System.out.print("Enter the Vehicle number:\t");
          String vehicleNumber = scanner.next();
          if (!Vehicle.validateVehicleNumber(vehicleNumber)) {
            System.out.println("Please enter valid vehicle number");
            break;
          }
          System.out.println("Your slot is '"
              + parkingLot.parkVehicle(vehicleNumber) + "'");
          break;

        case CHOICE_2:
          System.out.print("Enter the vehicle number:\t");
          String vehiclenumber = scanner.next();
          if (!Vehicle.validateVehicleNumber(vehiclenumber)) {
            System.out.println("Please enter valid vehicle number");
            break;
          }
          parkingLot.unparkVehicle(vehiclenumber);
          break;

        case CHOICE_3:
          System.out.print("Enter the vehicle number:\t");
          String vehicleID = scanner.next();
          if (!Vehicle.validateVehicleNumber(vehicleID)) {
            System.out.println("Please enter valid vehicle number");
            break;
          }
          parkingLot.checkStatus(vehicleID);
          break;

        case CHOICE_4:
          System.exit(0);
          scanner.close();
          break;
        default:
          System.out.println("Please choose a correct option :(");
      }
    } while (true);
  }

}
