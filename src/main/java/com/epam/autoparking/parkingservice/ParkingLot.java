package com.epam.autoparking.parkingservice;

import com.epam.autoparking.Vehicle;
import com.epam.autoparking.persistance.FileReadFailedException;
import com.epam.autoparking.persistance.Log;
import com.epam.autoparking.persistance.TransactionHandler;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

/**
 * ParkingLot class manages the parking vehicles and realted data.
 */
public class ParkingLot {

  /**
   * array maintains the slots of parking lot.
   */
  private Slot[] slots;

  /**
   * max parking lot size.
   */
  private int maxParkingLotSize;

  /**
   * current number of vehicles in the parking lot.
   */
  private int noOfVehiclesInLot;

  /**
   * Constuctor.
   * Initializes the slots.
   * @param size The size of the parking lot.
   */
  public ParkingLot(final int size) {
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

  /**
   * function to get the vehicle slot number.
   * @param id Vehicle registration number
   * @return return the slot number if present else returns -1
   */
  private int indexOfVehicle(final String id) {
    Vehicle current = new Vehicle(id);
    int i = current.hashCode() % maxParkingLotSize;
    int startIndex = (i - 1) % maxParkingLotSize;
    for (; slots[i] != null; i = (i + 1) % maxParkingLotSize) {
      if (slots[i].getVehicle().equals(current)) {
        return i;
      }
      if (i == startIndex) {
        break;
      }
    }
    return -1;
  }

  /**
   * Parks a vehicle in the parking lot.
   * @param id Vehicle Registration number
   * @return Parking slot number
   */
  public int parkVehicle(final String id) throws PresentInLotException, ParkingLotFullException, IOException {
    // if the vehicle is already present no need to park
    if (isPresent(id)) {
      throw new PresentInLotException("Vehicle already present in parking lot");
    }

    // if the currentEmpty is -1 no slot is empty
    if (noOfVehiclesInLot == maxParkingLotSize) {
      throw new ParkingLotFullException("Parking lot is full");
    }

    Vehicle currentVehicle = new Vehicle(id);

    int indx = currentVehicle.hashCode() % maxParkingLotSize;
    while (slots[indx] != null) {
      indx = (indx + 1) % maxParkingLotSize;
    }
    slots[indx] = new Slot();
    LocalDateTime vehicleInTime = LocalDateTime.now();
    slots[indx].assignVehicle(currentVehicle, vehicleInTime);
    noOfVehiclesInLot++;

    // Transaction file handling entry
    TransactionHandler transactionHandler = TransactionHandler.getInstance();
    transactionHandler.writeEntry(id, indx, vehicleInTime);

    return indx;
  }

  /**
   * Unparks a vehicle.
   * @param id Vehicle Registration number
   * @return returns the status. -1 if fails. 0 if success
   */
  public int unparkVehicle(final String id) throws NotPresentInLotException, FileReadFailedException, IOException {
    // if the vehicle is not present
    if (!isPresent(id)) {
      throw new NotPresentInLotException("Vehicle not present in parking lot");
    }

    // get the slot of the vehicle in the parking lot
    int vehicleSlot = indexOfVehicle(id);
    slots[vehicleSlot].printDetails();
    System.out.println("Removed Vehicle");

    // transaction file removal of entry
    TransactionHandler transactionHandler = TransactionHandler.getInstance();
    transactionHandler.deleteEntryById(id);

    // logging the entry
    Vehicle v = slots[vehicleSlot].getVehicle();
    Log log = Log.getInstance();
    log.write(
        v.getId(),
        Integer.toString(vehicleSlot),
        slots[vehicleSlot].getInTime().toString(),
        LocalDateTime.now().toString(),
        Long.toString(
            slots[vehicleSlot].getInTime().until(
                LocalDateTime.now(),
                ChronoUnit.MINUTES)
        )
    );
    log.close();

    slots[vehicleSlot] = null;
    return 0;
  }

  /**
   * gives details of the vehicle in the parking.
   * @param id Vehicle Registration number
   */
  public void checkStatus(final String id) throws NotPresentInLotException {
    // if vehicle is not present
    if (!isPresent(id)) {
      throw new NotPresentInLotException("Vehicle Not present in Lot.");
    }
    int slotNumber = indexOfVehicle(id);
    slots[slotNumber].printDetails();
  }

  /**
   * assign a slot the vehicle from the transaction file.
   * @param id vehicle registration number
   * @param slotNo slotnumber in parking lot
   * @param inTime time and date of the parking vehicle
   */
  public void assignSlot(final String id, final int slotNo,
                         final LocalDateTime inTime) {
    slots[slotNo] = new Slot();
    slots[slotNo].assignVehicle(new Vehicle(id), inTime);
    noOfVehiclesInLot++;
  }

}
