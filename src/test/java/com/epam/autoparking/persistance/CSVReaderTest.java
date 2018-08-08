package com.epam.autoparking.persistance;

import com.epam.autoparking.persistance.CSVReader;
import com.epam.autoparking.persistance.FileReadFailedException;
import org.junit.Test;

public class CSVReaderTest {

  @Test(expected = FileReadFailedException.class)
  public void test() throws FileReadFailedException {
    CSVReader csvReader = CSVReader.getInstance();
    try {
      csvReader.readFromFile("path/does/not/exits");
    } catch(FileReadFailedException e) {
      throw e;
    }
  }

}
