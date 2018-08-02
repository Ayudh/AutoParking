package com.epam.autoparking;

import com.epam.autoparking.utils.CSVWriter;

/**
 * Log class contains operations to write data to log file.
 */
public final class Log {

  /**
   * instance of DataWriter.
   */
  private DataWriter dataWriter;

  /**
   * string to specify the path location.
   */
  private String filePath = "src/main/resources/log.csv";

  /**
   * instance of log class.
   */
  private static Log logInstance;

  /**
   * private constructor.
   */
  private Log() {
    dataWriter = new CSVWriter(filePath, true);
  }

  /**
   * follows singleton pattern.
   * @return instance of log class
   */
  public static Log getInstance() {
    if (logInstance == null) {
      logInstance = new Log();
    }
    return logInstance;
  }

  /**
   * method to set filepath of log file.
   * @param path file path of log file
   */
  public void setFilePath(final String path) {
    filePath = path;
    dataWriter = new CSVWriter(filePath, true);
  }

  /**
   * writes fields specified to the log file.
   * @param strings variable length args to write to fields.
   */
  public void write(final String... strings) {
    dataWriter.writeRow(strings);
  }

  /**
   * close the resources.
   */
  public void close() {
    dataWriter.close();
    logInstance = null;
  }

  public void clear() {
    dataWriter = new CSVWriter(filePath, false);
    dataWriter.close();
  }

}
