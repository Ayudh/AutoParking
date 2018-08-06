package com.epam.autoparking;

import com.epam.autoparking.exceptions.FileReadFailedException;
import com.epam.autoparking.exceptions.NotPresentInLotException;
import com.epam.autoparking.exceptions.ParkingLotFullException;
import com.epam.autoparking.exceptions.PresentInLotException;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Test class for ParkingLot.
 */
public class ParkingLotTest {

  /**
   * initializes the log file path.
   */
  @Before
  public void setUp() throws IOException {
    Log.getInstance().setFilePath("src/test/resources/log.csv");
  }
  /**
   * parking lot size to test.
   */
  private static final int SIZE = 4;
  /**
   * tests park method.
   */
  @Test
  public void testPark() throws PresentInLotException, ParkingLotFullException, IOException {
    ParkingLot parkingLot = new ParkingLot(SIZE);
    assertNotEquals(-1, parkingLot.parkVehicle("AP01Q1234"));
    assertNotEquals(-1, parkingLot.parkVehicle("AP01W1234"));
    assertNotEquals(-1, parkingLot.parkVehicle("AP02QQ1234"));
  }

  @Test(expected = PresentInLotException.class)
  public void testParkPresent() throws PresentInLotException, ParkingLotFullException, IOException {
    ParkingLot parkingLot = new ParkingLot(SIZE);
    assertNotEquals(-1, parkingLot.parkVehicle("AP01Q1234"));
    parkingLot.parkVehicle("AP01Q1234");
  }

  /**
   * test unpark method.
   */
  @Test(expected = NotPresentInLotException.class)
  public void testUnpark() throws NotPresentInLotException, PresentInLotException, ParkingLotFullException, FileReadFailedException, IOException {
    ParkingLot parkingLot = new ParkingLot(SIZE);
    parkingLot.parkVehicle("AP01Q1234");
    assertEquals(0, parkingLot.unparkVehicle("AP01Q1234"));
     parkingLot.unparkVehicle("AP01Q1234");
  }

  @Test(expected = ParkingLotFullException.class)
  public void testParkInFull() throws PresentInLotException, ParkingLotFullException, IOException {
    ParkingLot parkingLot = new ParkingLot(SIZE);
    assertNotEquals(-1, parkingLot.parkVehicle("AP01Q1234"));
    assertNotEquals(-1, parkingLot.parkVehicle("AP01W1234"));
    assertNotEquals(-1, parkingLot.parkVehicle("AP02QQ1234"));
    assertNotEquals(-1, parkingLot.parkVehicle("AP02AQ1234"));
    assertNotEquals(-1, parkingLot.parkVehicle("AP02BQ1234"));
  }

  @Test(expected = NotPresentInLotException.class)
  public void testCheckStatus() throws NotPresentInLotException {
    ParkingLot parkingLot = new ParkingLot(SIZE);
    parkingLot.checkStatus("AP01Q1234");

  }
}
