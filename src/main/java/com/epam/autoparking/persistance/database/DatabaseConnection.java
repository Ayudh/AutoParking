package com.epam.autoparking.persistance.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
  private DatabaseConnection() {

  }

  private static Connection connection;

  public static Connection getConnection() throws ClassNotFoundException, SQLException {
    if (connection == null) {
      Class.forName("com.mysql.jdbc.Driver");
      System.out.println("[INFO] Loaded jdbc driver");
      connection = DriverManager.getConnection("jdbc:mysql://localhost/autoparking", "root", "root");
    }
    return connection;
  }
}
