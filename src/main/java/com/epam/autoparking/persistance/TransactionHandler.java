package com.epam.autoparking.persistance;


import com.epam.autoparking.persistance.database.DatabaseConnection;
import com.epam.autoparking.persistance.database.ParkingLotSizeDatabase;
import com.epam.autoparking.persistance.database.TransactionDatabase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

public class TransactionHandler {
  private static TransactionHandler transactionHandler;
  private TransactionDatabase transactionDatabase;
  private ParkingLotSizeDatabase parkingLotSizeDatabase;

  private TransactionHandler() throws SQLException, ClassNotFoundException {
    transactionDatabase = TransactionDatabase.getInstance();
    parkingLotSizeDatabase = ParkingLotSizeDatabase.getInstance();
  }

  public static TransactionHandler getInstance() throws SQLException, ClassNotFoundException {
    if (transactionHandler == null)
      transactionHandler = new TransactionHandler();
    return transactionHandler;
  }

  public void writeEntry(final String vehicleId, final int slotnumber,
                         final LocalDateTime intime) throws SQLException {
    transactionDatabase.writeEntry(vehicleId, slotnumber, intime);
  }

  public void writeSize(int parkingSize) throws SQLException {
    parkingLotSizeDatabase.writeSize(parkingSize);
  }

  public DataFormat readRows() throws SQLException {
    return transactionDatabase.readRows();
  }

  public void deleteEntryById(final String id) throws SQLException {
    transactionDatabase.deleteEntryById(id);
  }

  public void clear() throws SQLException {
    transactionDatabase.clear();
  }

  public boolean isValid() {
    return true;
  }

  public boolean exists() {
    return true;
  }

  public int getParkingLotSize() throws SQLException {
    return parkingLotSizeDatabase.getParkingLotSize();
  }

}
