package com.epam.autoparking;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class CSVWriter {
  private Writer writer;
  CSVWriter(String fileName, boolean append) {
    try {
      writer = new FileWriter(fileName, append);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void writeRow(String... fields) {
    String row = "";
    for (int i=0;i<fields.length-1;i++) {
      row = row + fields[i] + ",";
    }
    row = row + fields[fields.length - 1] + "\n";
    write(row);
  }

  private void write(String s) {
    try {
      writer.write(s);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void close() {
    try {
      writer.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

}
