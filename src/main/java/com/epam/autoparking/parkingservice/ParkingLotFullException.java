package com.epam.autoparking.parkingservice;

public class ParkingLotFullException extends Exception {
  public ParkingLotFullException(String message) {
    super(message);
  }
}
