package com.epam.autoparking.persistance;

import java.util.List;

/**
 * class specifies the format of the data that needs to be persisted.
 */
public class DataFormat {

  /**
   * to store the matrix form of data.
   */
  private List<List<String>> rows;

  /**
   * constructor.
   * @param text the data
   */
  public DataFormat(final List<List<String>> text) {
    this.rows = text;
  }

  /**
   * returns all rows.
   * @return rows in DataFormat pattern
   */
  public List<List<String>> getAllRows() {
    return rows;
  }

  /**
   * to get a specific row.
   * @param i denotes the specific row number to be returned
   * @return the list of fields in the specific row
   */
  public List<String> getRow(final int i) {
    return rows.get(i);
  }

  /**
   * the size of data.
   * @return the number of rows in the data
   */
  public int noOfRows() {
    return rows.size();
  }

}
