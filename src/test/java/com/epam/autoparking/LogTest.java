package com.epam.autoparking;

import com.epam.autoparking.utils.CSVReader;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

public class LogTest {

  @Test
  public void testWrite() throws IOException {

    Log.getInstance().setFilePath("src/test/resources/log.csv");
    Log log = Log.getInstance();
    log.close();
    DataFormat data = CSVReader.getInstance().readFromFile("src/test/resources/log.csv");
    for (List<String> row : data.getAllRows()) {
      for (String fields : row) {
        System.out.print(fields + " ");
      }
      System.out.println();
    }
    log.clear();
  }

}