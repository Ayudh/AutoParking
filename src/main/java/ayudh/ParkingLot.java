package ayudh;

import java.util.HashMap;

/**
 * ParkingLot class manages the parking vehicles and realted data.
 */
class ParkingLot {

  /**
   * array maintains the slots of parking lot.
   */
  private Slot[] slots;

  /**
   * array maintains a link of the empty slots dynamically.
   */
  private int[] nextAvailable;

  /**
   * key value pair to store and retrieve vehicle number along with slot number.
   */
  private HashMap<String, Integer> slotMap;

  /**
   * a slot number where the current vehicle should be stored in parking slots.
   */
  private int currentEmpty;

  /**
   * Constuctor.
   * Initializes the slots.
   * nextavailable array and currentEmpty to work according.
   * @param size The size of the parking lot.
   */
  ParkingLot(final int size) {
    slots = new Slot[size];
    nextAvailable = new int[size];
    for (int i = 0; i < size; i++) {
      slots[i] = new Slot();
      nextAvailable[i] = i + 1;
    }
    nextAvailable[size - 1] = -1;
    slotMap = new HashMap<String, Integer>();
    currentEmpty = 0;
  }

  /**
   * function which check whether a vehicle is present in the parking lot.
   * @param id Vehicle registration number.
   * @return true if Present. false otherwise.
   */
  private boolean isPresent(final String id) {
    return slotMap.containsKey(id);
  }

  /**
   * function adds the vehicle with registration
   * to a specified slot in the parking lot.
   * @param id Vehicle Registration number
   * @param slotNumber Slot number in the parking lot
   */
  private void addToSlotMap(final String id, final int slotNumber) {
    slotMap.put(id, slotNumber);
  }

  /**
   * Returns the slot number of the vehicle.
   * @param id Vehicle Registration number
   * @return returns slot number in the parking slots
   */
  private int getSlotNumber(final String id) {
    return slotMap.get(id);
  }

  /**
   * removes the slot of the specified vehicle.
   * @param id Vehicle Registration number
   */
  private void removeSlotNumber(final String id) {
    slotMap.remove(id);
  }

  /**
   * Parks a vehicle in the parking lot.
   * @param id Vehicle Registration number
   * @return Parking slot number
   */
  int parkVehicle(final String id) {
    // if the vehicle is already present no need to park
    if (isPresent(id)) {
      System.out.println("Already present in the parking");
      return -1;
    }

    // if the currentEmpty is -1 no slot is empty
    if (currentEmpty == -1) {
      System.out.println("Parking lot is full. :(");
      return -1;
    }

    int presentVehicleSlot = currentEmpty;
    slots[presentVehicleSlot].assignVehicle(new Vehicle(id));
    addToSlotMap(id, presentVehicleSlot);

    // update the currentEmpty by following the nextAvailable chain
    currentEmpty = nextAvailable[currentEmpty];

    return presentVehicleSlot;
  }

  /**
   * Unparks a vehicle.
   * @param id Vehicle Registration number
   * @return returns the status. -1 if fails. 0 if success
   */
  int unparkVehicle(final String id) {
    // if the vehicle is not present
    if (!isPresent(id)) {
      System.out.println("Vehicle is not present");
      return -1;
    }

    // get the slot of the vehicle in the parking lot
    int vehicleSlot = getSlotNumber(id);
    slots[vehicleSlot].printDetails();
    System.out.println("Removed Vehicle");
    slots[vehicleSlot].removeVehicle();

    // link this empty slot so it can be used to park another vehicle
    nextAvailable[vehicleSlot] = currentEmpty;
    currentEmpty = vehicleSlot;
    removeSlotNumber(id);
    return 0;
  }

  /**
   * gives details of the vehicle in the parking.
   * @param id Vehicle Registration number
   */
  void checkStatus(final String id) {
    // if vehicle is not present
    if (!isPresent(id)) {
      System.out.println("Vehicle is not present in the parking lot");
      return;
    }
    int slotNumber = getSlotNumber(id);
    slots[slotNumber].printDetails();
  }

}
