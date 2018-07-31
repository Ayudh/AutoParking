package com.epam.autoparking;

import java.util.List;

public class CSVFormat {
  private List<List<String>> rows;

  CSVFormat(List<List<String>> text) {
    this.rows = text;
  }

  public List<List<String>> getAllRows() {
    return rows;
  }

  public List<String> getRow(int i) {
    return rows.get(i);
  }

  public int noOfRows() {
    return rows.size();
  }

}
