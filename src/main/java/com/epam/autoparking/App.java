package com.epam.autoparking;

import com.epam.autoparking.parkingservice.NotPresentInLotException;
import com.epam.autoparking.parkingservice.ParkingLot;
import com.epam.autoparking.parkingservice.ParkingLotFullException;
import com.epam.autoparking.parkingservice.PresentInLotException;
import com.epam.autoparking.persistance.FileReadFailedException;
import com.epam.autoparking.persistance.TransactionHandler;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.regex.Pattern;

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
   * method checks whether arguments passed are valid.
   * @param args array of strings to validate
   * @return true if valid, false otherwise
   */
  private static boolean validArgs(final String[] args) {
    boolean status = true;

    if (args.length < 3) {
      status = false;
    } else if (args.length > 4) {

      status = false;
    } else if (args.length == 3 && !args[2].equals("0")) {
      status = false;
    } else if (args.length == 4
        && !(args[2].equals("1")
        && Pattern.compile("\\d+").matcher(args[3]).matches())) {
      status = false;
    }

    return status;
  }

  /**
   * Main method Starting point of execution.
   * @param args command line parameters
   * @throws FileReadFailedException when the transaction file reading fails
   * @throws IOException when error in writing to file
   */
  public static void main(final String[] args)
      throws FileReadFailedException, IOException, SQLException, ClassNotFoundException {

    scanner = new Scanner(System.in);

    if (!validArgs(args)) {
      System.out.println("Please enter valid command line arguments");
      System.out.println("usage: <username> <password> <0/1> <slotsize if 1>");
      System.exit(1);
    }

    String userName = args[0];
    String password = args[1];

    if (Login.validateUser(userName, password) != -1) {
      System.out.println("Error: Authentication failed. "
          + "Username or Password is incorrect.");
      return;
    }

    System.out.println("[INFO]\tAuthenticated");

    int parkingLotSize;
    ParkingLot parkingLot;

    TransactionHandler transactionHandler = TransactionHandler.getInstance();

    if (args[2].equals("0")) {
      parkingLot = ParkingLot.loadFromTransactionFile();
    } else {
      transactionHandler.clear();
      parkingLotSize = Integer.parseInt(args[3]);
      parkingLot = new ParkingLot(parkingLotSize);
      transactionHandler.writeSize(parkingLotSize);
    }

    boolean status = true;

    do {
      int choice = getUserChoice();

      String vehicleNumber = null;
      if (choice >= 1 && choice <= 3) {
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

  /**
   * checks the status of vechicle in the parking lot.
   * @param parkingLot object for parkinglot
   * @param vehicleID vehicle registration number
   */
  private static void checkStatus(final ParkingLot parkingLot,
                                  final String vehicleID) {
    try {
      String message = parkingLot.checkStatus(vehicleID);
      System.out.println(message);
    } catch (NotPresentInLotException e) {
      System.out.println("Not present in the parking lot");
    }
  }

  /**
   * unparks the vehicle from the parking lot.
   *
   * @param parkingLot    parking lot object
   * @param vehiclenumber vehicle registration number
   * @throws FileReadFailedException throws when reading from the file fails
   * @throws IOException             throws when handling file fails
   */
  private static void unpark(final ParkingLot parkingLot,
                             final String vehiclenumber)
      throws FileReadFailedException, IOException, SQLException, ClassNotFoundException {
    try {
      String message = parkingLot.unparkVehicle(vehiclenumber);
      System.out.println(message);
    } catch (NotPresentInLotException e) {
      System.out.println("Not present in the parking lot");
    }
  }

  /**
   * parks the vehicle in the parking lot.
   * @param parkingLot parking lot object to park
   * @param vehicleNumber vehicle registration number
   * @throws IOException throws when file handling fails
   */
  private static void park(final ParkingLot parkingLot,
                           final String vehicleNumber) throws IOException, SQLException, ClassNotFoundException {
    try {
      System.out.println("Your slot is '"
          + parkingLot.parkVehicle(vehicleNumber) + "'");
    } catch (PresentInLotException e) {
      System.out.println("Vehicle present in the parking lot");
    } catch (ParkingLotFullException e) {
      System.out.println("Parking lot is full");
    }
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
    return scanner.nextInt();
  }

}
