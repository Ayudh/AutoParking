package com.epam.autoparking.persistance.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ParkingLotSizeDatabase {
  private static ParkingLotSizeDatabase parkingLotSizeDatabase;
  private Connection connection;

  private ParkingLotSizeDatabase() throws SQLException, ClassNotFoundException {
    connection = DatabaseConnection.getConnection();
  }

  public static ParkingLotSizeDatabase getInstance() throws SQLException, ClassNotFoundException {
    if (parkingLotSizeDatabase == null)
      parkingLotSizeDatabase = new ParkingLotSizeDatabase();
    return parkingLotSizeDatabase;
  }

  public void writeSize(int parkingSize) throws SQLException {
    PreparedStatement statement = connection.prepareStatement("UPDATE parkinglotsize SET parkingsize = ?");
    statement.setInt(1, parkingSize);
    statement.executeUpdate();
  }

  public int getParkingLotSize() throws SQLException {
    PreparedStatement statement = connection.prepareStatement("SELECT parkingsize FROM parkinglotsize");
    ResultSet resultSet = statement.executeQuery();
    resultSet.next();
    return resultSet.getInt(1);
  }

}
