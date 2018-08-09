package com.epam.autoparking.persistance;

public class FileReadFailedException extends Exception {
  public FileReadFailedException(String message) {
    super(message);
  }
}
