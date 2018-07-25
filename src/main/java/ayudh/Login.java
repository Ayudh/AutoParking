package ayudh;

/**
 * Login class to validate the admin.
 */
final class Login {

  /**
   * private constructor.
   */
  private Login() {
  }

  /**
   * list of usernames to validate.
   */
  private static String[] users = {"hari"};

  /**
   * list of passwords in order of usernames.
   */
  private static String[] passwords = {"hari"};

  /**
   * function to validate the username and password.
   * @param userName Admin username to be validated
   * @param password Admin password to be validated
   * @return true if both username and password is matched and false otherwise
   */
  static boolean validateUser(final String userName, final String password) {
    int i = 0;
    for (; i < users.length; i++) {
      if (users[i].equalsIgnoreCase(userName)) {
        return passwords[i].equals(password);
      }
    }
    return false;
  }

}
