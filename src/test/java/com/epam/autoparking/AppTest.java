package com.epam.autoparking;

import com.epam.autoparking.utils.CSVReader;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;

import static org.junit.Assert.*;

public class AppTest {

  @Before
  public void setup() {
    Log.getInstance().setFilePath("src/test/resources/log.csv");
    TransactionHandler.getInstance().setFilePath("src/test/resources/transaction.csv");
  }

  @Test
  public void testMainTransactionEmpty() {
    Log.getInstance().clear();
    TransactionHandler.getInstance().clear();
    String input = "admin\nadmin\n20\n4";
    ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(input.getBytes());
    System.setIn(byteArrayInputStream);
    App.main(null);
    assertTrue(TransactionHandler.getInstance().exists());
  }

  @Test
  public void testMainPark() {
    String input = "admin\nadmin\n20\n1\nAP01W1234\n4";
    ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(input.getBytes());
    System.setIn(byteArrayInputStream);
    App.main(null);
    DataFormat data = CSVReader.getInstance().readFromFile("src/test/resources/transaction.csv");
    boolean found = false;
    for (int i = 1;i<data.noOfRows();i++) {
      if (data.getRow(i).get(0).equals("AP01W1234")) {
        found = true;
        break;
      }
    }

    assertTrue(found);
  }

  @Test
  public void testMainUnpark() {
    testMainPark();
    String input = "admin\nadmin\n2\nAP01W1234\n4";
    ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(input.getBytes());
    System.setIn(byteArrayInputStream);
    App.main(null);
  }

  @Test
  public void testMainLoadTransaction() {
    String input = "admin\nadmin\n20\n1\nAP01W1234\n4";
    ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(input.getBytes());
    System.setIn(byteArrayInputStream);
    App.main(null);
  }

  @Test
  public void testMainCheckStatus() {
    String input = "admin\nadmin\n3\nAP01W1234\n4";
    ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(input.getBytes());
    System.setIn(byteArrayInputStream);
    App.main(null);
  }

  @Test
  public void testMainInvalidLogin() {
    String input = "admin\nadmi\n";
    ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(input.getBytes());
    System.setIn(byteArrayInputStream);
    App.main(null);
  }

}