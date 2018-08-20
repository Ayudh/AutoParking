package com.epam.autoparking.servlets;

import com.epam.autoparking.Vehicle;
import com.epam.autoparking.parkingservice.NotPresentInLotException;
import com.epam.autoparking.parkingservice.ParkingLot;
import com.epam.autoparking.parkingservice.ParkingLotFullException;
import com.epam.autoparking.parkingservice.PresentInLotException;
import com.epam.autoparking.persistance.FileReadFailedException;
import com.epam.autoparking.persistance.database.TransactionHandlerDatabase;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet(urlPatterns = "/home")
public class HomeServlet extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    resp.sendRedirect("home.jsp");
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String option = req.getParameter("option");
    String vehicleid = req.getParameter("vehicleid");
    String message = "";
    if (Vehicle.validateVehicleNumber(vehicleid)) {
      ParkingLot parkingLot;
      try {
        parkingLot = ParkingLot.loadFromTransactionFile();
      } catch (Exception e) {
        e.printStackTrace();
        System.out.println("error in fetching parking size");
        parkingLot = new ParkingLot(20);
        try {
          TransactionHandlerDatabase.getInstance().writeSize(20);
        } catch (SQLException e1) {
          e1.printStackTrace();
        } catch (ClassNotFoundException e1) {
          e1.printStackTrace();
        }
      }
      switch (option) {
        case "park":
          try {
            int slotNumber = parkingLot.parkVehicle(vehicleid);
            message = "Your slot Number is " + slotNumber;
          } catch (PresentInLotException e) {
            message = "Already present in parkinglot";
          } catch (ParkingLotFullException e) {
            message = "Parking Lot Full";
          } catch (SQLException e) {
            e.printStackTrace();
          } catch (ClassNotFoundException e) {
            message = "Database Driver not loaded";
          }
          break;
        case "unpark":
          try {
            message = parkingLot.unparkVehicle(vehicleid)+"\nUnparked Successfully.";
          } catch (NotPresentInLotException e) {
            message = "Vehicle Not Present in Lot";
          } catch (FileReadFailedException e) {
            message = "Failed";
          } catch (SQLException e) {
            e.printStackTrace();
          } catch (ClassNotFoundException e) {
            message = "Error in loading driver";
          }
          break;
        case "status":
          try {
            message = parkingLot.checkStatus(vehicleid);
          } catch (NotPresentInLotException e) {
            message = "Not present in parking lot";
          }
          break;
      }
    } else {
      message = "Not a valid vehicle Number";
    }
//    req.setAttribute("message", message);
//    req.getRequestDispatcher("home.jsp").forward(req, resp);
    PrintWriter printWriter = resp.getWriter();
    printWriter.write(message);
    printWriter.flush();
  }
}
