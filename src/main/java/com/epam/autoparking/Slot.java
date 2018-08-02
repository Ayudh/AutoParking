package com.epam.autoparking;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

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
  private LocalDateTime inTime;

  /**
   * to get the intime of vehicle.
   * @return intime in LocalDataTime format
   */
  public LocalDateTime getInTime() {
    return inTime;
  }

  /**
   * assigns a vehicle to slot.
   * @param v vehicle to be assigned
   */
  void assignVehicle(final Vehicle v) {
    this.vehicle = v;
    inTime = LocalDateTime.now();
  }

  /**
   * assign vehicle the time and slot.
   * @param v vehicle registration number
   * @param vehicleInTime in time of vehicle
   */
  void assignVehicle(final Vehicle v, final LocalDateTime vehicleInTime) {
    this.vehicle = v;
    this.inTime = vehicleInTime;
  }

  /**
   * to get vehicle in the slot.
   * @return Vehicle object in the slot
   */
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
        + (inTime.until(LocalDateTime.now(), ChronoUnit.MINUTES)) + " minutes");

  }

  /**
   * removes the vehicle in the slot.
   */
  void removeVehicle() {
    vehicle = null;
    inTime = null;
  }

}
