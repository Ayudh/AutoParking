package com.epam.autoparking;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Test class for ParkingLot.
 */
public class ParkingLotTest {
  /**
   * parking lot size to test.
   */
  private static final int SIZE = 4;
  /**
   * tests park method.
   */
  @Test
  public void testPark() {
    ParkingLot parkingLot = new ParkingLot(SIZE);
    assertNotEquals(-1, parkingLot.parkVehicle("AP01Q1234"));
    assertNotEquals(-1, parkingLot.parkVehicle("AP02QQ1234"));
  }

  /**
   * test unpark method.
   */
  @Test
  public void testUnpark() {
    ParkingLot parkingLot = new ParkingLot(SIZE);
    parkingLot.parkVehicle("AP01Q1234");
    assertEquals(0, parkingLot.unparkVehicle("AP01Q1234"));
    assertEquals(-1, parkingLot.unparkVehicle("AP01Q1234"));
  }
}
