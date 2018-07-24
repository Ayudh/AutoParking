package ayudh;

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

    System.out.print("Enter the size of parking lot:");
    int parkingLotSize = scanner.nextInt();
    ParkingLot parkingLot = new ParkingLot(parkingLotSize);

    do {
      System.out.println("Enter your choice:");
      System.out.println("1.Park a vehicle\n" 
      + "2.Unpark a vehicle\n"
          + "3.Check status of a vehicle\n4.Exit");
      int choice = scanner.nextInt();
      switch (choice) {
        case 1:
          System.out.print("Enter the Vehicle number:\t");
          String vehicleNumber = scanner.next();
          if (!Vehicle.validateVehicleNumber(vehicleNumber)) {
            System.out.println("Please enter valid vehicle number");
            break;
          }
          System.out.println("Your slot is '" + parkingLot.parkVehicle(vehicleNumber) + "'");
          break;
        case 2:
          System.out.print("Enter the vehicle number:\t");
          String vehiclenumber = scanner.next();
          if (!Vehicle.validateVehicleNumber(vehiclenumber)) {
            System.out.println("Please enter valid vehicle number");
            break;
          }
          parkingLot.unparkVehicle(vehiclenumber);
          break;
        case 3:
          System.out.print("Enter the vehicle number:\t");
          String vehicleID = scanner.next();
          if (!Vehicle.validateVehicleNumber(vehicleID)) {
            System.out.println("Please enter valid vehicle number");
            break;
          }
          parkingLot.checkStatus(vehicleID);
          break;
        case 4:
          System.exit(0);
          scanner.close();
          break;
        default:
          System.out.println("Please choose a correct option :(");
      }
    } while (true);
  }

}
