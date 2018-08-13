package com.epam.autoparking;

import java.util.regex.Pattern;

/**
 * holds the properties of vehicle.
 */
public class Vehicle {

  /**
   * Vehicle Registration number.
   */
  private String id;

  /**
   * constant to calculate the hashcode.
   */
  private static final int MODULO = 0x7fffffff;

  /**
   * Constuctor.
   * takes Vehicle Registration number as input
   * @param vehicleID Vehicle Registration number
   */
  public Vehicle(final String vehicleID) {
    this.id = vehicleID;
  }

  /**
   * Getter method for ID.
   * @return ID of the vehicle
   */
  public String getId() {
    return id;
  }

  /**
   * validates the Vehicle id number.
   * @param id Vehicle Registration number
   * @return true if matches specified pattern. false, otherwise
   */
  public static boolean validateVehicleNumber(final String id) {
    Pattern p = Pattern.compile("(AP|TS)\\d\\d\\w{1,2}\\d\\d\\d\\d");
    return p.matcher(id).matches();
  }

  @Override
  public int hashCode() {
    return id.hashCode() & MODULO;
  }

  @Override
  public boolean equals(final Object obj) {
    return id.equals(((Vehicle) obj).id);
  }
}
