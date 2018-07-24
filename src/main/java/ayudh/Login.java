package ayudh;

public class Login {
  private static String[] users = {"hari"};
  private static String[] passwords = {"hari"};

  static boolean validateUser(String userName, String password) {
    int i = 0;
    for (;i<users.length;i++) {
      if (users[i].equalsIgnoreCase(userName)) {
        return passwords[i].equals(password);
      }
    }
    return false;
  }

}
