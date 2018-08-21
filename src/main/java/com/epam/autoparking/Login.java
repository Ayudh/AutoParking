package com.epam.autoparking;

import com.epam.autoparking.persistance.database.UsersDatabase;

import java.sql.SQLException;

/**
 * Login class to validate the admin.
 */
public final class Login {

  /**
   * private constructor.
   */
  private Login() {
  }

  /**
   * function to validate the username and password.
   * @param userName Admin username to be validated
   * @param password Admin password to be validated
   * @return true if both username and password is matched and false otherwise
   */
  public static int validateUser(final String userName, final String password) {
    try {
      int role = UsersDatabase.getInstance().getUserRole(userName, password);
      return role;
    } catch (SQLException | ClassNotFoundException e) {
      e.printStackTrace();
      return -1;
    }
  }

}
