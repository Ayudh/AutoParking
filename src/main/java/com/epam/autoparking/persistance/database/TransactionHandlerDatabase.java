package com.epam.autoparking.persistance.database;


import com.epam.autoparking.persistance.DataFormat;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

public class TransactionHandlerDatabase {
  private static TransactionHandlerDatabase transactionHandlerDatabase;
  private Connection connection;

  private TransactionHandlerDatabase() throws SQLException, ClassNotFoundException {
    connection = DatabaseConnection.getConnection();
  }

  public static TransactionHandlerDatabase getInstance() throws SQLException, ClassNotFoundException {
    if (transactionHandlerDatabase == null)
      transactionHandlerDatabase = new TransactionHandlerDatabase();
    return transactionHandlerDatabase;
  }

  public void writeEntry(final String vehicleId, final int slotnumber,
                         final LocalDateTime intime) throws SQLException {
    PreparedStatement statement = connection.prepareStatement("INSERT INTO transaction (vehicleid, slotnumber, intime ) VALUES (?,?,?)");
    statement.setString(1, vehicleId);
    statement.setInt(2, slotnumber);
    statement.setString(3, intime.toString());
    statement.executeUpdate();
  }

  public void writeSize(int parkingSize) throws SQLException {
    PreparedStatement statement = connection.prepareStatement("UPDATE parkinglotsize SET parkingsize = ?");
    statement.setInt(1, parkingSize);
    statement.executeUpdate();
  }

  public DataFormat readRows() throws SQLException {
    PreparedStatement statement = connection.prepareStatement("SELECT vehicleid, slotnumber, intime FROM transaction");
    ResultSet resultSet = statement.executeQuery();
    List<List<String>> records = new LinkedList<>();
    while (resultSet.next()) {
      String vehicleid = resultSet.getString(1);
      String slotnumber = Integer.toString(resultSet.getInt(2));
      String intime = resultSet.getString(3);
      List<String> row = new LinkedList<>();
      row.add(vehicleid);
      row.add(slotnumber);
      row.add(intime);
      records.add(row);
    }
    return new DataFormat(records);
  }

  public void deleteEntryById(final String id) throws SQLException {
    PreparedStatement statement = connection.prepareStatement("DELETE FROM transaction WHERE vehicleid = ?");
    statement.setString(1, id);
    statement.executeUpdate();
  }

  public void clear() throws SQLException {
    PreparedStatement statement = connection.prepareStatement("TRUNCATE TABLE transaction");
    statement.executeUpdate();
  }

  public boolean isValid() {
    return true;
  }

  public boolean exists() {
    return true;
  }

  public int getParkingLotSize() throws SQLException {
    PreparedStatement statement = connection.prepareStatement("SELECT parkingsize FROM parkinglotsize");
    ResultSet resultSet = statement.executeQuery();
    resultSet.next();
    return resultSet.getInt(1);
  }

}
