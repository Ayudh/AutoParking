package com.epam.autoparking;

import com.epam.autoparking.exceptions.FileReadFailedException;
import com.epam.autoparking.exceptions.NotPresentInLotException;
import com.epam.autoparking.exceptions.ParkingLotFullException;
import com.epam.autoparking.exceptions.PresentInLotException;

import java.io.IOException;
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
   * instance of scanner so methods can use.
   */
  private static Scanner scanner;

  /**
   * Main method Starting point of execution.
   * @param args command line parameters
   */
  public static void main(final String[] args) throws FileReadFailedException, IOException {

    scanner = new Scanner(System.in);

    // Authenticating the admin
    System.out.print("Enter admin username:\t");
    String userName = scanner.next();
    System.out.print("Enter password:\t");
    String password = scanner.next();

    if (!Login.validateUser(userName, password)) {
      System.out.println("Error: Authentication failed. "
          + "Username or Password is incorrect.");
      return;
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
        parkingLot.assignSlot(id, slotNumber, inTime);
      }
    } else {

      // Admin enters the size of parking lot
      System.out.print("Enter the size of parking lot:");
      parkingLotSize = scanner.nextInt();
      parkingLot = new ParkingLot(parkingLotSize);
      transactionHandler.writeSize(parkingLotSize);
    }

    boolean status = true;

    do {
      int choice = getUserChoice();

      String vehicleNumber = null;
      if (choice >=1 && choice <= 3) {
        vehicleNumber = getValidVehicleID();
        if (vehicleNumber == null) {
          System.out.println("Please enter valid vehicle number");
          continue;
        }
      }

      switch (choice) {

        case CHOICE_1:
          park(parkingLot, vehicleNumber);
          break;

        case CHOICE_2:

          unpark(parkingLot, vehicleNumber);
          break;

        case CHOICE_3:
          checkStatus(parkingLot, vehicleNumber);
          break;

        case CHOICE_4:
          status = false;
          scanner.close();
          break;
        default:
          System.out.println("Please choose a correct option :(");
      }
    } while (status);
  }

  private static void checkStatus(ParkingLot parkingLot, String vehicleID) {
    try {
      parkingLot.checkStatus(vehicleID);
    } catch (NotPresentInLotException e) {
      System.out.println("Not present in the parking lot");
    }
  }

  private static void unpark(ParkingLot parkingLot, String vehiclenumber) throws FileReadFailedException, IOException {
    try {
      parkingLot.unparkVehicle(vehiclenumber);
    } catch (NotPresentInLotException e) {
      System.out.println("Not present in the parking lot");
    }
  }

  private static void park(ParkingLot parkingLot, String vehicleNumber) throws IOException {
    try {
      System.out.println("Your slot is '"
          + parkingLot.parkVehicle(vehicleNumber) + "'");
    } catch (PresentInLotException e) {
      System.out.println("Vehicle present in the parking lot");
    } catch (ParkingLotFullException e) {
      System.out.println("Parking lot is full");
    }
    return;
  }

  /**
   * method to take input from console.
   * @return string vehicle id
   */
  private static String getValidVehicleID() {
    System.out.print("Enter the Vehicle number:\t");
    String input = scanner.next();
    if (!Vehicle.validateVehicleNumber(input)) {
      input = null;
    }
    return input;

  }

  /**
   * method to take user choice from console.
   * @return int user choice
   */
  private static int getUserChoice() {
    System.out.println("Enter your choice:");
    System.out.println("1.Park a vehicle\n"
    + "2.Unpark a vehicle\n"
        + "3.Check status of a vehicle\n4.Exit");

    scanner.nextLine();
    return scanner.nextInt();
  }

}
