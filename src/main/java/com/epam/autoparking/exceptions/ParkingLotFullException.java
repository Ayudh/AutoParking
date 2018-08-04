package com.epam.autoparking.exceptions;

public class ParkingLotFullException extends Exception {
  public ParkingLotFullException(String message) {
    super(message);
  }
}
