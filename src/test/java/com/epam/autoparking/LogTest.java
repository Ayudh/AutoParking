package com.epam.autoparking;

import com.epam.autoparking.exceptions.FileReadFailedException;
import com.epam.autoparking.utils.CSVReader;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

/**
 * Test class for Log class.
 */
public class LogTest {

  /**
   * test method for writing in log.
   */
  @Test
  public void testWrite() throws FileReadFailedException, IOException {

    Log.getInstance().setFilePath("src/test/resources/log.csv");
    Log log = Log.getInstance();
    log.write("asdf", "asdf", "asdf");
    log.close();
    DataFormat data = CSVReader.getInstance()
        .readFromFile("src/test/resources/log.csv");
    for (List<String> row : data.getAllRows()) {
      for (String fields : row) {
        System.out.print(fields + " ");
      }
      System.out.println();
    }
    log.clear();
  }

}
