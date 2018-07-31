package com.epam.autoparking;

public class Log {
  private CSVWriter csvWriter;
  private String filePath = "src/main/resources/log.csv";

  private static Log logInstance;

  private Log() {
    csvWriter = new CSVWriter(filePath, true);
  }

  public static Log getInstance() {
    if (logInstance == null)
      logInstance = new Log();
    return logInstance;
  }

  public void write(String... strings) {
    csvWriter.writeRow(strings);
  }

  public void close() {
    csvWriter.close();
    logInstance = null;
  }

}
