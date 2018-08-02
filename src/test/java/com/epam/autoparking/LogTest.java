package com.epam.autoparking;

import com.epam.autoparking.utils.CSVReader;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.junit.Assert.*;

public class LogTest {

  @Rule
  public TemporaryFolder temporaryFolder = new TemporaryFolder();

  @Test
  public void testWrite() throws IOException {

    File logFile = temporaryFolder.newFile("log.csv");
    Log.getInstance().setFilePath(logFile.toString());
    Log log = Log.getInstance();
    log.write("hari", "ayudh");
    log.close();
    DataFormat data = CSVReader.getInstance().readFromFile(logFile.toString());
    for (List<String> row : data.getAllRows()) {
      for (String fields : row) {
        System.out.print(fields + " ");
      }
      System.out.println();
    }
  }

}