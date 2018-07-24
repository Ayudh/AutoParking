package ayudh;

import java.time.LocalTime;

public class Slot {
  private Vehicle vehicle;
  private LocalTime inTime;

  void assignVehicle (Vehicle vehicle) {
    this.vehicle = vehicle;
    inTime = LocalTime.now();
  }

  void printDetails() {
    if (vehicle == null)
      return;
    System.out.println("Vehicle with ID "+vehicle.getId()+" for " + (LocalTime.now().getMinute() - inTime.getMinute()) + " minutes");
  }

  void removeVehicle () {
    vehicle = null;
    inTime = null;
  }

}
