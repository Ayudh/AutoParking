package com.epam.autoparking.parkingservice;

public class NotPresentInLotException extends Exception {
  public NotPresentInLotException(String message) {
    super(message);
  }
}
