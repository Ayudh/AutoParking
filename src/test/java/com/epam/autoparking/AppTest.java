package com.epam.autoparking;

import com.epam.autoparking.persistance.DataFormat;
import com.epam.autoparking.persistance.Log;
import com.epam.autoparking.persistance.FileReadFailedException;
import com.epam.autoparking.persistance.TransactionHandler;
import com.epam.autoparking.persistance.CSVReader;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import static org.junit.Assert.assertTrue;

/**
 * Test class for App class.
 */
public class AppTest {

  /**
   * method initializes the transaction and log file.
   * @throws IOException throws when the file handling fails
   */
  @Before
  public void setup() throws IOException {
    Log.getInstance().setFilePath("src/test/resources/log.csv");
    TransactionHandler.getInstance()
        .setFilePath("src/test/resources/transaction.csv");
    TransactionHandler.getInstance().clear();
  }

  /**
   * test method for transaction file empty.
   * @throws FileReadFailedException throws when file read fails
   * @throws IOException throws when file handling fails
   */
  @Test
  public void testMainTransactionEmpty() throws
      FileReadFailedException, IOException {
    Log.getInstance().clear();
    TransactionHandler.getInstance().clear();
    String input = "4\n";
    ByteArrayInputStream byteArrayInputStream =
        new ByteArrayInputStream(input.getBytes());
    System.setIn(byteArrayInputStream);
    App.main(new String[]{"admin", "admin", "1", "20"});
    assertTrue(TransactionHandler.getInstance().exists());
  }

  /**
   * test method for park method.
   * @throws FileReadFailedException throws when file read fails
   * @throws IOException throws when file handling fails
   */
  @Test
  public void testMainPark() throws FileReadFailedException, IOException {
    String input = "1\nAP01AA1234\n1\nAP01W1234\n4";
    ByteArrayInputStream byteArrayInputStream =
        new ByteArrayInputStream(input.getBytes());
    System.setIn(byteArrayInputStream);
    App.main(new String[]{"admin", "admin", "1", "20"});
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
   * test the App class main method for vehicle presnt.
   * @throws FileReadFailedException throws when file read fails
   * @throws IOException throws when file handling fails
   */
  @Test
  public void testMainParkVehiclePresent()
      throws FileReadFailedException, IOException {
    String input = "1\nAP01W1234\n1\nAP01W1234\n1\nAP01A1234\n4\n";
    ByteArrayInputStream byteArrayInputStream =
        new ByteArrayInputStream(input.getBytes());
    System.setIn(byteArrayInputStream);
    App.main(new String[]{"admin", "admin", "1", "10"});

  }

  /**
   * test method for unpark method.
   * @throws FileReadFailedException throws when file read fails
   * @throws IOException throws when file handling fails
   */
  @Test
  public void testMainUnpark() throws FileReadFailedException, IOException {
    testMainPark();
    String input = "2\nAP01W1234\n4";
    ByteArrayInputStream byteArrayInputStream =
        new ByteArrayInputStream(input.getBytes());
    System.setIn(byteArrayInputStream);
    App.main(new String[]{"admin", "admin", "0"});
  }

  /**
   * test app class main method for unpark not present vehicle.
   * @throws FileReadFailedException throws when file read fails
   * @throws IOException throws when file handling fails
   */
  @Test
  public void testMainUnparkNotPresent()
      throws FileReadFailedException, IOException {
    testMainPark();
    String input = "2\nAP01Z1234\n4";
    ByteArrayInputStream byteArrayInputStream =
        new ByteArrayInputStream(input.getBytes());
    System.setIn(byteArrayInputStream);
    App.main(new String[]{"admin", "admin", "0"});
  }

  /**
   * test method for loading from trasaction file.
   * @throws FileReadFailedException throws when file read fails
   * @throws IOException throws when file handling fails
   */
  @Test
  public void testMainLoadTransaction()
      throws FileReadFailedException, IOException {
    String input = "1\nAP01W1234\n4";
    ByteArrayInputStream byteArrayInputStream =
        new ByteArrayInputStream(input.getBytes());
    System.setIn(byteArrayInputStream);
    App.main(new String[]{"admin", "admin", "1", "20"});

  }

  /**
   * test method for checking status of the vehicle.
   * @throws FileReadFailedException throws when file read fails
   * @throws IOException throws when file handling fails
   */
  @Test
  public void testMainCheckStatus()
      throws FileReadFailedException, IOException {
    String input = "1\nAP01W1234\n3\nAP01W1234\n4";
    ByteArrayInputStream byteArrayInputStream =
        new ByteArrayInputStream(input.getBytes());
    System.setIn(byteArrayInputStream);
    App.main(new String[]{"admin", "admin", "1", "20"});
  }

  /**
   * tests app class main method for checking status of not present.
   * @throws FileReadFailedException throws when file read fails
   * @throws IOException throws when file handling fails
   */
  @Test
  public void testMainCheckStatusNotPresent()
      throws FileReadFailedException, IOException {
    String input = "1\nAP01W1234\n3\nAP01W0234\n4";
    ByteArrayInputStream byteArrayInputStream =
        new ByteArrayInputStream(input.getBytes());
    System.setIn(byteArrayInputStream);
    App.main(new String[]{"admin", "admin", "1", "20"});
  }

  /**
   * test main method for invalid option.
   * @throws FileReadFailedException throws when file read fails
   * @throws IOException throws when file handling fails
   */
  @Test
  public void testMainInvalidOption()
      throws FileReadFailedException, IOException {
    String input = "20\n5\n4\n";
    ByteArrayInputStream byteArrayInputStream =
        new ByteArrayInputStream(input.getBytes());
    System.setIn(byteArrayInputStream);
    App.main(new String[]{"admin", "admin", "1", "20"});
  }

  /**
   * test method for invalid login.
   * @throws FileReadFailedException throws when file read fails
   * @throws IOException throws when file handling fails
   */
  @Test
  public void testMainInvalidLogin()
      throws FileReadFailedException, IOException {
    App.main(new String[]{"admin", "admi", "0"});
  }

  /**
   * test main method for invalid vehicle id.
   * @throws FileReadFailedException throws when file read fails
   * @throws IOException throws when file handling fails
   */
  @Test
  public void testMainInvalidVehicleId()
      throws FileReadFailedException, IOException {
    String input = "1\nAP01WWW1234\n4\n";
    ByteArrayInputStream byteArrayInputStream =
        new ByteArrayInputStream(input.getBytes());
    System.setIn(byteArrayInputStream);
    App.main(new String[]{"admin", "admin", "1", "20"});
  }

}
