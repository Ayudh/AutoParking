package ayudh;

import java.util.regex.Pattern;

/**
 * holds the properties of vehicle.
 */
class Vehicle {

  /**
   * Vehicle Registration number.
   */
  private String id;

  /**
   * Constuctor.
   * takes Vehicle Registration number as input
   * @param vehicleID Vehicle Registration number
   */
  Vehicle(final String vehicleID) {
    this.id = vehicleID;
  }

  /**
   * Getter method for ID.
   * @return ID of the vehicle
   */
  String getId() {
    return id;
  }

  /**
   * validates the Vehicle id number.
   * @param id Vehicle Registration number
   * @return true if matches specified pattern. false, otherwise
   */
  static boolean validateVehicleNumber(final String id) {
    Pattern p = Pattern.compile("(AP|TS)\\d\\d\\w{1,2}\\d\\d\\d\\d");
    return p.matcher(id).matches();
  }
}