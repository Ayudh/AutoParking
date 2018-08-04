package com.epam.autoparking.exceptions;

public class NotPresentInLotException extends Exception {
  public NotPresentInLotException(String message) {
    super(message);
  }
}
