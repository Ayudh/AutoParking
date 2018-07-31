package com.epam.autoparking;

import java.time.LocalTime;

public class TransactionHandler {

  CSVWriter csvWriter;

  String filePath = "src/main/resources/transaction.csv";

  private static TransactionHandler transactionHandlerInstance;

  private TransactionHandler() {

  }

  public TransactionHandler getInstance() {
    if (transactionHandlerInstance == null)
      transactionHandlerInstance = new TransactionHandler();
    return transactionHandlerInstance;
  }

  public void writeEntry(String id, int slotNumber, LocalTime inTime) {
    CSVWriter csvWriter = new CSVWriter(filePath, true);
    csvWriter.writeRow(id, Integer.toString(slotNumber), inTime.toString());
    csvWriter.close();
  }

  public CSVFormat readRows() {
    return CSVReader.readCSV(filePath);
  }

  public void deleteEntryById(String id) {
    CSVFormat data = readRows();
    CSVWriter csvWriter = new CSVWriter(filePath, false);
    for (int i = 0;i < data.noOfRows();i++) {
      if (data.getRow(i).get(0).equals(id)) {
        continue;
      }
      csvWriter.writeRow(data.getRow(i).toArray(new String[1]));
    }
    csvWriter.close();
  }

}
