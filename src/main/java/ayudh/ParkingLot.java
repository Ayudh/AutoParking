package ayudh;

/**
 * ParkingLot class manages the parking vehicles and realted data.
 */
class ParkingLot {

  /**
   * array maintains the slots of parking lot.
   */
  private Slot[] slots;

  private int maxParkingLotSize;

  private int noOfVehiclesInLot;

  /**
   * Constuctor.
   * Initializes the slots.
   * nextavailable array and currentEmpty to work according.
   * @param size The size of the parking lot.
   */
  ParkingLot(final int size) {
    maxParkingLotSize = size;
    slots = new Slot[size];
    noOfVehiclesInLot = 0;
  }

  /**
   * function which check whether a vehicle is present in the parking lot.
   * @param id Vehicle registration number.
   * @return true if Present. false otherwise.
   */
  private boolean isPresent(final String id) {
    return indexOfVehicle(id) != -1;
  }

  private int indexOfVehicle(String id) {
    Vehicle current = new Vehicle(id);
    int i = current.hashCode() % maxParkingLotSize;
    int startIndex = (i-1) % maxParkingLotSize;
    for (; slots[i] != null; i = (i + 1) % maxParkingLotSize) {
      if (slots[i].getVehicle().equals(current)) {
        return i;
      }
      if (i == startIndex)
        break;
    }
    return -1;
  }

  /**
   * function adds the vehicle with registration
   * to a specified slot in the parking lot.
   * @param id Vehicle Registration number
   * @param slotNumber Slot number in the parking lot
   */
//  private void addToSlotMap(final String id, final int slotNumber) {
//    slotMap.put(id, slotNumber);
//  }

  /**
   * Returns the slot number of the vehicle.
   * @param id Vehicle Registration number
   * @return returns slot number in the parking slots
   */
//  private int getSlotNumber(final String id) {
//    return slotMap.get(id);
//  }

  /**
   * removes the slot of the specified vehicle.
   * @param id Vehicle Registration number
   */
//  private void removeSlotNumber(final String id) {
//    slotMap.remove(id);
//  }

  /**
   * Parks a vehicle in the parking lot.
   * @param id Vehicle Registration number
   * @return Parking slot number
   */
  int parkVehicle(final String id) {
    // if the vehicle is already present no need to park
    if (isPresent(id)) {
      System.out.println("Already present in the parking");
      return indexOfVehicle(id);
    }

    // if the currentEmpty is -1 no slot is empty
    if (noOfVehiclesInLot == maxParkingLotSize) {
      System.out.println("Parking lot is full. :(");
      return -1;
    }

    Vehicle currentVehicle = new Vehicle(id);

    int indx = currentVehicle.hashCode() % maxParkingLotSize;
    for (; slots[indx] != null; indx = (indx + 1) % maxParkingLotSize) {
    }
    slots[indx] = new Slot();
    slots[indx].assignVehicle(currentVehicle);
    noOfVehiclesInLot++;
    return indx;
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
    int vehicleSlot = indexOfVehicle(id);
    slots[vehicleSlot].printDetails();
    System.out.println("Removed Vehicle");
    slots[vehicleSlot] = null;

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
    int slotNumber = indexOfVehicle(id);
    slots[slotNumber].printDetails();
  }

}
