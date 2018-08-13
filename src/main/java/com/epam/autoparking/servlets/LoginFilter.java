package com.epam.autoparking.servlets;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter(urlPatterns = {"/home"})
public class LoginFilter implements Filter {

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {

  }

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
    HttpServletRequest httpServletRequest = (HttpServletRequest) request;
    if (httpServletRequest.getSession().getAttribute("username") != null) {
      chain.doFilter(request, response);
    } else {
      httpServletRequest.getRequestDispatcher("/login").forward(request, response);
    }
  }

  @Override
  public void destroy() {

  }
}
