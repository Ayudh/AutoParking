package com.epam.autoparking;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Test class for Vehicle.
 */
public class TestVehicle {
  /**
   * test validating vehicle number.
   */
  @Test
  public void testValidateVehicleNumber() {
    assertEquals(true, Vehicle.validateVehicleNumber("AP01Q1234"));
    assertEquals(true, Vehicle.validateVehicleNumber("AP01QQ1234"));
    assertEquals(false, Vehicle.validateVehicleNumber("AP0QQ1234"));
  }
}
