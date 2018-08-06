package com.epam.autoparking;

import java.io.IOException;

/**
 * interface to abstract writing data.
 */
public interface DataWriter {

  /**
   * write specified rows.
   * @param fields variable length args to write.
   */
  void writeRow(String... fields) throws IOException;

  /**
   * close all the unwanted resources.
   */
  void close() throws IOException;
}
