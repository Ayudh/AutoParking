package ayudh;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Test class for ParkingLot.
 */
public class TestParkingLot {
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
    assertEquals(0, parkingLot.parkVehicle("AP01Q1234"));
    assertEquals(1, parkingLot.parkVehicle("AP02QQ1234"));
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
