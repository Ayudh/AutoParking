package ayudh;

import java.util.regex.Pattern;

public class Vehicle {
  private String id;
  Vehicle (String id) {
    this.id = id;
  }
  String getId() {
    return id;
  }
  static boolean validateVehicleNumber(String id) {
    Pattern p = Pattern.compile("(AP|TS)\\d\\d\\w{1,2}\\d\\d\\d\\d");
    return p.matcher(id).matches();
  }
}
