package com.epam.autoparking.parkingservice;

public class PresentInLotException extends Exception {
  public PresentInLotException(String message) {
    super(message);
  }
}
