package com.epam.autoparking.servlets;

import com.epam.autoparking.persistance.DataFormat;
import com.epam.autoparking.persistance.FileReadFailedException;
import com.epam.autoparking.persistance.TransactionHandler;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/getDetails")
public class GetDetailsServlet extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    JsonArray jsonArray = new JsonArray();
    try {
      DataFormat data = TransactionHandler.getInstance().readRows();
      for (int i = 1; i < data.noOfRows(); i++) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("vehicleid", data.getRow(i).get(0));
        jsonObject.addProperty("slotnumber", data.getRow(i).get(1));
        jsonObject.addProperty("intime", data.getRow(i).get(2));
        jsonArray.add(jsonObject);
      }
    } catch (FileReadFailedException e) {
      e.printStackTrace();
    }
    resp.setContentType("application/json");
    PrintWriter printWriter = resp.getWriter();
    printWriter.write(jsonArray.toString());
    printWriter.flush();
  }
}
