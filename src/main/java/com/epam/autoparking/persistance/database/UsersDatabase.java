package com.epam.autoparking.persistance.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsersDatabase {
  private static UsersDatabase usersDatabase;
  private Connection connection;

  private UsersDatabase() throws SQLException, ClassNotFoundException {
    connection = DatabaseConnection.getConnection();
  }

  public static UsersDatabase getInstance() throws SQLException, ClassNotFoundException {
    if (usersDatabase == null)
      usersDatabase = new UsersDatabase();
    return usersDatabase;
  }

  public int getUserRole(String username, String password) {
    try {
      PreparedStatement statement = connection.prepareStatement("SELECT password, usertype FROM users where username = ?");
      statement.setString(1, username);
      ResultSet resultSet = statement.executeQuery();
      if (resultSet.next() == false) {
        return -1;
      }
      else {
        if (resultSet.getString(1).equals(password)) {
          return resultSet.getInt(2);
        }
      }
    } catch (SQLException e) {
      e.printStackTrace();
      return -1;
    }
    return -1;
  }

}
