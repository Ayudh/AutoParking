package com.epam.autoparking.persistance.database;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

public class Demo {

  public static void main(String[] args) {
    try {
      Class.forName("com.mysql.jdbc.Driver");
      System.out.println("[INFO] Loaded jdbc driver");
      Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/autoparking", "root", "root");

      // inserting into database
//      PreparedStatement statement = connection.prepareStatement("INSERT INTO users VALUES(?,?,?)");
//      statement.setString(1, "admin2");
//      statement.setString(2, "root");
//      statement.setInt(3, 2);
//      statement.executeUpdate();
//      System.out.println("Added data into users table");

      // fetcing data from database
      PreparedStatement statement = connection.prepareStatement("SELECT username, password, usertype FROM users WHERE username=?");
      statement.setString(1, "hari");
      ResultSet resultSet = statement.executeQuery();
      resultSet.last();
      System.out.println(resultSet.getRow());
      resultSet.beforeFirst();
      while (resultSet.next()) {
        String username = resultSet.getNString(1);
        String password = resultSet.getNString(2);
        Integer usertype = resultSet.getInt(3);
        System.out.println(username+" " + password+" " + usertype);
      }

    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

}
