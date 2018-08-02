package com.epam.autoparking;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * Test class for Login.
 */
public class LoginTest {
  /**
   * test method for validating user.
   */
  @Test
  public void testValidateUser() {
    assertEquals(true, Login.validateUser("hari", "hari"));
    assertEquals(true, Login.validateUser("admin", "admin"));
    assertEquals(false, Login.validateUser("hari", "admin"));
    assertEquals(false, Login.validateUser("ayudh", "admin"));
  }
}
