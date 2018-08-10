package com.epam.autoparking;

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
   * list of usernames to validate.
   */
  private static String[] users = {"hari", "admin"};

  /**
   * list of passwords in order of usernames.
   */
  private static String[] passwords = {"hari", "admin"};

  /**
   * function to validate the username and password.
   * @param userName Admin username to be validated
   * @param password Admin password to be validated
   * @return true if both username and password is matched and false otherwise
   */
  public static boolean validateUser(final String userName, final String password) {
    for (int i = 0; i < users.length; i++) {
      if (users[i].equalsIgnoreCase(userName)) {
        return passwords[i].equals(password);
      }
    }
    return false;
  }

}
