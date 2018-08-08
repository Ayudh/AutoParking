package com.epam.autoparking.persistance;

import com.epam.autoparking.persistance.CSVWriter;
import org.junit.Test;

import java.io.IOException;

public class CSVWriterTest {

  @Test(expected = IOException.class)
  public void test() throws IOException {
    CSVWriter csvWriter = new CSVWriter("src/test/resources/permission_denied.txt", false);
  }

}
