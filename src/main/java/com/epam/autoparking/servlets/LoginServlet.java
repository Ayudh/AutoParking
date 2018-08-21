package com.epam.autoparking.servlets;

import com.epam.autoparking.Login;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/login")
public class LoginServlet extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    if (req.getSession().getAttribute("username") != null) {
      System.out.println("[DEBUG]redirecting already login");
      resp.sendRedirect("home");
      return;
    }
    req.getRequestDispatcher("login.jsp").forward(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String username = req.getParameter("username");
    String password = req.getParameter("password");
    int role = Login.validateUser(username, password);
    if (role != -1) {
      req.getSession().setAttribute("username", username);
      req.getSession().setAttribute("userrole", role);
      resp.sendRedirect("home");
    } else {
      req.setAttribute("message", "Invalid Username or password");
      req.getRequestDispatcher("login.jsp").forward(req, resp);
    }
  }
}
