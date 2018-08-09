package com.epam.autoparking.persistance;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * CSVReader class is a DataReader.
 * contains methods to read data from csv file
 */
public final class CSVReader implements DataReader {

  /**
   * instance of CSVReader.
   */
  private static CSVReader instance;

  /**
   * private constructor.
   */
  private CSVReader() {

  }

  /**
   * following singleton pattern.
   * @return the instance of CSVReader
   */
  public static CSVReader getInstance() {
    if (instance == null) {
      instance = new CSVReader();
    }
    return instance;
  }

  /**
   * reads the data from file and returns the csv file as DataFormat Object.
   * @param filePath file system path to the file that needs to be read
   * @return Data from the csv file as DataFormat
   */
  public DataFormat readFromFile(final String filePath) throws FileReadFailedException {

    String line;

    List<List<String>> rows = new ArrayList<>();

    try (BufferedReader bufferedReader = new BufferedReader(
        new FileReader(filePath))
    ) {

      while ((line = bufferedReader.readLine()) != null) {
        List<String> row = new ArrayList<>();
        for (String s : line.split(",")) {
          row.add(s.trim());
        }
        rows.add(row);
      }

    } catch (IOException e) {
      throw new FileReadFailedException("Failure in reading from file");
    }

    return new DataFormat(rows);
  }

}
