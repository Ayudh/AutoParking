package com.epam.autoparking.utils;

import static org.junit.Assert.*;

import org.junit.Test;

import java.io.IOException;

public class CSVWriterTest {

  @Test(expected = IOException.class)
  public void test() throws IOException {
    CSVWriter csvWriter = new CSVWriter("src/test/resources/permission_denied.txt", false);
  }

}
