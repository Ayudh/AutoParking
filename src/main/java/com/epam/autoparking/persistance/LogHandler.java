package com.epam.autoparking.persistance;

import com.epam.autoparking.persistance.database.LogDatabase;

import java.sql.SQLException;

public class LogHandler {

  private static LogHandler logHandler;
  private LogDatabase logDatabase;

  private LogHandler() throws SQLException, ClassNotFoundException {
    logDatabase = LogDatabase.getInstance();
  }

  public static LogHandler getInstance() throws SQLException, ClassNotFoundException {
    if (logHandler == null)
      logHandler = new LogHandler();
    return logHandler;
  }

  public void write(String vehicleId, String slotnumber, String intime, String outtime, int duration) throws SQLException {
    logDatabase.write(vehicleId, slotnumber, intime, outtime, duration);
  }

  public void close() throws SQLException {
    logHandler = null;
  }

}
