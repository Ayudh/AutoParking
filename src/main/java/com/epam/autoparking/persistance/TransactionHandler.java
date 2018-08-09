package com.epam.autoparking.persistance;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;

/**
 * class to handle transactions.
 */
public final class TransactionHandler {

  /**
   * filepath of the transaction file.
   */
  private String filePath = "src/main/resources/transaction.csv";

  /**
   * instance of transaction handler.
   */
  private static TransactionHandler transactionHandlerInstance;

  /**
   * private constructor.
   */
  private TransactionHandler() {

  }

  /**
   * gets instance of TransactionHandler.
   * @return instance of transaction
   */
  public static TransactionHandler getInstance() {
    if (transactionHandlerInstance == null) {
      transactionHandlerInstance = new TransactionHandler();
    }
    return transactionHandlerInstance;
  }

  /**
   * method to set transaction file.
   * @param path path to the transaction file
   */
  public void setFilePath(final String path) {
    filePath = path;
  }

  /**
   * writes a entry to the transaction file.
   * @param id vehicle registration number
   * @param slotNumber slot number in the parking area
   * @param inTime in-time of vehicle
   */
  public void writeEntry(final String id, final int slotNumber,
                         final LocalDateTime inTime) throws IOException {
    DataWriter dataWriter = new CSVWriter(filePath, true);
    dataWriter.writeRow(id, Integer.toString(slotNumber), inTime.toString());
    dataWriter.close();
  }

  /**
   * writes the size of parking lot on the first line of file.
   * @param parkingSize size of parking lot
   */
  public void writeSize(final int parkingSize) throws IOException {
    DataWriter dataWriter = new CSVWriter(filePath, true);
    dataWriter.writeRow(Integer.toString(parkingSize));
    dataWriter.close();
  }

  /**
   * to read all rows of the transactions.
   * @return data in DataFormat
   */
  public DataFormat readRows() throws FileReadFailedException {
    return CSVReader.getInstance().readFromFile(filePath);
  }

  /**
   * deletes entry of transaction file by id.
   * @param id vehicle registration number
   */
  public void deleteEntryById(final String id) throws FileReadFailedException, IOException {
    DataFormat data = readRows();
    DataWriter dataWriter = new CSVWriter(filePath, false);
    for (int i = 0; i < data.noOfRows(); i++) {
      if (data.getRow(i).get(0).equals(id)) {
        continue;
      }
      dataWriter.writeRow(data.getRow(i).toArray(new String[1]));
    }
    dataWriter.close();
  }

  /**
   * checks whehter a file is valid or not.
   * @return returns true if file is valid. False, otherwise.
   */
  public boolean isValid() throws FileReadFailedException {
    if (!exists()) {
      return false;
    }
    DataFormat data = CSVReader.getInstance().readFromFile(filePath);
    if (data.noOfRows() == 0) {
      return false;
    }
    return true;
  }

  /**
   * checks if the file exists or not.
   * @return True if file exists. False, Otherwise.
   */
  public boolean exists() {
    File file = new File(filePath);
    return file.exists();
  }

  /**
   * clears the transaction file.
   */
  public void clear() throws IOException {
    DataWriter dataWriter = new CSVWriter(filePath, false);
    dataWriter.close();
  }

}
