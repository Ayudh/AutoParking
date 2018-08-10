package com.epam.autoparking.servlets;


import com.epam.autoparking.Login;
import com.epam.autoparking.persistance.Log;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/login")
public class LoginServlet extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    resp.sendRedirect("login.jsp");
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    PrintWriter writer = resp.getWriter();
    String username = req.getParameter("username");
    String password = req.getParameter("password");
    if (Login.validateUser(username, password)) {
      resp.sendRedirect("home");
    } else {
      req.setAttribute("message", "Invalid Username or password");
      req.getRequestDispatcher("login.jsp").forward(req, resp);
    }
  }
}