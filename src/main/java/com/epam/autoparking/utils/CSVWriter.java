package com.epam.autoparking.utils;

import com.epam.autoparking.DataWriter;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

/**
 * CSVWriter class is a DataWriter.
 * contains methods to write data to the csv file
 */
public class CSVWriter implements DataWriter {

  /**
   * Writer class variable to write data to the file.
   */
  private Writer writer;

  /**
   * construnctor.
   * @param filePath points to the file that needs to be written
   * @param append append status. specify true, if append should be done.
   */
  public CSVWriter(final String filePath, final boolean append) {
    try {
      writer = new FileWriter(filePath, append);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * writes a row to the csv file.
   * @param fields variable length arguments to write any
   * number of fields to the csv file.
   */
  public void writeRow(final String... fields) {
    String row = "";
    for (int i = 0; i < fields.length - 1; i++) {

      row = row + fields[i] + ",";
    }
    row = row + fields[fields.length - 1] + "\n";
    write(row);
  }

  /**
   * private method to write a string to the file.
   * @param s string to be written
   */
  private void write(final String s) {
    try {
      writer.write(s);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * method to close the file resources.
   */
  public void close() {
    try {
      writer.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

}
