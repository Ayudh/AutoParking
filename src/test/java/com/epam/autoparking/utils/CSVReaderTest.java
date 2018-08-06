package com.epam.autoparking.utils;

import static org.junit.Assert.*;

import com.epam.autoparking.exceptions.FileReadFailedException;
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
