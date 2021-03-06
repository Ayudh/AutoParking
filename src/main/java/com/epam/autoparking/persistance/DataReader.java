package com.epam.autoparking.persistance;

/**
 * interface to abstract data reading.
 */
public interface DataReader {

  /**
   * method to read data from file.
   * @param filePath specifies the file location
   * @return the data in DataFormat
   */
  DataFormat readFromFile(String filePath) throws FileReadFailedException;
}
