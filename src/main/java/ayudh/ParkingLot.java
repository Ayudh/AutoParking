package ayudh;

import java.util.HashMap;

/**
 * ParkingLot class manages the parking vehicles and realted data.
 */
public class ParkingLot {

  Slot slots[];
  int nextAvailable[];
  HashMap<String, Integer> slotMap;
  int currentEmpty;

  ParkingLot(int size) {
    slots = new Slot[size];
    nextAvailable = new int[size];
    for (int i = 0;i< size;i++) {
      slots[i] = new Slot();
      nextAvailable[i] = i + 1;
    }
    nextAvailable[size-1] = -1;
    slotMap = new HashMap<String, Integer>();
    currentEmpty = 0;
  }

  private boolean isPresent (String id) {
    return slotMap.containsKey(id);
  }

  private void addToSlotMap(String id, int slotNumber) {
    slotMap.put(id, slotNumber);
  }

  private int getSlotNumber(String id) {
    return slotMap.get(id);
  }

  private void removeSlotNumber(String id) {
    slotMap.remove(id);
  }

  int parkVehicle(String id) {
    if (isPresent(id)) {
      System.out.println("Already present in the parking");
      return -1;
    }

    if (currentEmpty == -1) {
      System.out.println("Parking lot is full. :(");
      return -1;
    }

    int presentVehicleSlot = currentEmpty;
    slots[presentVehicleSlot].assignVehicle(new Vehicle(id));
    addToSlotMap(id, presentVehicleSlot);

    currentEmpty = nextAvailable[currentEmpty];

    return presentVehicleSlot;
  }

  void unparkVehicle(String id) {
    if (!isPresent(id)) {
      System.out.println("Vehicle is not present");
      return;
    }
    int vehicleSlot = getSlotNumber(id);
    slots[vehicleSlot].printDetails();
    System.out.println("Removed Vehicle");
    slots[vehicleSlot].removeVehicle();
    nextAvailable[vehicleSlot] = currentEmpty;
    currentEmpty = vehicleSlot;
    removeSlotNumber(id);
  }

  void checkStatus(String id) {
    if (!isPresent(id)) {
      System.out.println("Vehicle is not present in the parking lot");
      return;
    }
    int slotNumber = getSlotNumber(id);
    slots[slotNumber].printDetails();
  }

}
