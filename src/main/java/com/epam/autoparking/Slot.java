package com.epam.autoparking;

import java.time.LocalTime;

/**
 * Represents a single slot in parking lot.
 */
class Slot {

  /**
   * vehicle instance in the slot.
   */
  private Vehicle vehicle;

  /**
   * Time object to store the InTime of vehicle.
   */
  private LocalTime inTime;

  /**
   * assigns a vehicle to slot.
   * @param v vehicle to be assigned
   */
  void assignVehicle(final Vehicle v) {
    this.vehicle = v;
    inTime = LocalTime.now();
  }

  public Vehicle getVehicle() {
    return vehicle;
  }

  /**
   * prints the vehicle details like number of minutes.
   */
  void printDetails() {
    if (vehicle == null) {
      return;
    }
    System.out.println("Vehicle with ID " + vehicle.getId() + " for "
        + (LocalTime.now().getMinute() - inTime.getMinute()) + " minutes");
  }

  /**
   * removes the vehicle in the slot.
   */
  void removeVehicle() {
    vehicle = null;
    inTime = null;
  }

}
