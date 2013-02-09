package com.redhat.gss.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jboss.logging.Logger;

public class EchoUriServlet extends HttpServlet
{
  private Logger log = Logger.getLogger(this.getClass());

  public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException
  {
    String requestURI = request.getRequestURI();

    response.setContentType("text/plain");
    PrintWriter writer = null;
    try
    {
      writer = response.getWriter();
      writer.println(requestURI);
    }
    catch(IOException ioe)
    {
      log.error("Failed to write URL: " + requestURI);
    }
    finally
    {
      if(writer != null)
        writer.close();
    }
  }
}
