package com.epam.autoparking;

/**
 * interface to abstract writing data.
 */
public interface DataWriter {

  /**
   * write specified rows.
   * @param fields variable length args to write.
   */
  void writeRow(String... fields);

  /**
   * close all the unwanted resources.
   */
  void close();
}
