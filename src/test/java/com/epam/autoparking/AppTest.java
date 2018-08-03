package com.epam.autoparking;

import com.epam.autoparking.utils.CSVReader;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;

import static org.junit.Assert.assertTrue;

/**
 * Test class for App class.
 */
public class AppTest {

  /**
   * method initializes the transaction and log file.
   */
  @Before
  public void setup() {
    Log.getInstance().setFilePath("src/test/resources/log.csv");
    TransactionHandler.getInstance()
        .setFilePath("src/test/resources/transaction.csv");
  }

  /**
   * test method for transaction file empty.
   */
  @Test
  public void testMainTransactionEmpty() {
    Log.getInstance().clear();
    TransactionHandler.getInstance().clear();
    String input = "admin\nadmin\n20\n4";
    ByteArrayInputStream byteArrayInputStream =
        new ByteArrayInputStream(input.getBytes());
    System.setIn(byteArrayInputStream);
    App.main(null);
    assertTrue(TransactionHandler.getInstance().exists());
  }

  /**
   * test method for park method.
   */
  @Test
  public void testMainPark() {
    String input = "admin\nadmin\n20\n1\nAP01W1234\n4";
    ByteArrayInputStream byteArrayInputStream =
        new ByteArrayInputStream(input.getBytes());
    System.setIn(byteArrayInputStream);
    App.main(null);
    DataFormat data = CSVReader.getInstance()
        .readFromFile("src/test/resources/transaction.csv");
    boolean found = false;
    for (int i = 1; i < data.noOfRows(); i++) {
      if (data.getRow(i).get(0).equals("AP01W1234")) {
        found = true;
        break;
      }
    }

    assertTrue(found);
  }

  /**
   * test method for unpark method.
   */
  @Test
  public void testMainUnpark() {
    testMainPark();
    String input = "admin\nadmin\n2\nAP01W1234\n4";
    ByteArrayInputStream byteArrayInputStream =
        new ByteArrayInputStream(input.getBytes());
    System.setIn(byteArrayInputStream);
    App.main(null);
  }

  /**
   * test method for loading from trasaction file.
   */
  @Test
  public void testMainLoadTransaction() {
    String input = "admin\nadmin\n20\n1\nAP01W1234\n4";
    ByteArrayInputStream byteArrayInputStream =
        new ByteArrayInputStream(input.getBytes());
    System.setIn(byteArrayInputStream);
    App.main(null);
  }

  /**
   * test method for checking status of the vehicle.
   */
  @Test
  public void testMainCheckStatus() {
    String input = "admin\nadmin\n3\nAP01W1234\n4";
    ByteArrayInputStream byteArrayInputStream =
        new ByteArrayInputStream(input.getBytes());
    System.setIn(byteArrayInputStream);
    App.main(null);
  }

  /**
   * test method for invalid login.
   */
  @Test
  public void testMainInvalidLogin() {
    String input = "admin\nadmi\n";
    ByteArrayInputStream byteArrayInputStream =
        new ByteArrayInputStream(input.getBytes());
    System.setIn(byteArrayInputStream);
    App.main(null);
  }

}
