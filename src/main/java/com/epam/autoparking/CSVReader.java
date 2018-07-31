package com.epam.autoparking;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVReader {

  public static CSVFormat readCSV(String filePath) {

    String line;

    List<List<String>> rows = new ArrayList<>();

    try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))) {

      while ((line = bufferedReader.readLine()) != null) {
        List<String> row = new ArrayList<>();
        for (String s : line.split(",")) {
          row.add(s.trim());
        }
        rows.add(row);
      }

    } catch (IOException e) {
      e.printStackTrace();
    }
    return new CSVFormat(rows);
  }

}
