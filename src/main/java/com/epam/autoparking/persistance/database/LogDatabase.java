package com.epam.autoparking.persistance.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class LogDatabase {

  private static LogDatabase logDatabase;
  private Connection connection;

  private LogDatabase() throws SQLException, ClassNotFoundException {
    connection = DatabaseConnection.getConnection();
  }

  public static LogDatabase getInstance() throws SQLException, ClassNotFoundException {
    if (logDatabase == null)
      logDatabase = new LogDatabase();
    return logDatabase;
  }

  public void write(String vehicleId, String slotnumber, String intime, String outtime, int duration) throws SQLException {
    PreparedStatement statement = connection.prepareStatement("INSERT INTO log (vehicleid, slotnumber, intime, outtime, duration) VALUES (?,?,?,?,?)");
    statement.setString(1, vehicleId);
    statement.setString(2, slotnumber);
    statement.setString(3, intime);
    statement.setString(4, outtime);
    statement.setInt(5, duration);
    statement.executeUpdate();
  }

}
