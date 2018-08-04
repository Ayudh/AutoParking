package com.epam.autoparking;

import com.epam.autoparking.exceptions.NotPresentInLotException;
import com.epam.autoparking.exceptions.ParkingLotFullException;
import com.epam.autoparking.exceptions.PresentInLotException;
import org.junit.Before;
import org.junit.Test;

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
  public void setUp() {
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
  public void testPark() throws PresentInLotException, ParkingLotFullException {
    ParkingLot parkingLot = new ParkingLot(SIZE);
    assertNotEquals(-1, parkingLot.parkVehicle("AP01Q1234"));
    assertNotEquals(-1, parkingLot.parkVehicle("AP01Q1234"));
    assertNotEquals(-1, parkingLot.parkVehicle("AP02QQ1234"));
  }

  /**
   * test unpark method.
   */
  @Test
  public void testUnpark() throws NotPresentInLotException, PresentInLotException, ParkingLotFullException {
    ParkingLot parkingLot = new ParkingLot(SIZE);
    parkingLot.parkVehicle("AP01Q1234");
    assertEquals(0, parkingLot.unparkVehicle("AP01Q1234"));
    assertEquals(-1, parkingLot.unparkVehicle("AP01Q1234"));
  }
}
