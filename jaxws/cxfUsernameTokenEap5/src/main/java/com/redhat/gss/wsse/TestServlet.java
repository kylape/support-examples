package com.redhat.gss.wsse;

import java.io.IOException;
import javax.servlet.http.*;
import javax.servlet.ServletException;

public class TestServlet extends HttpServlet
{
  public void doGet(HttpServletRequest request, HttpServletResponse response)
    throws IOException, ServletException
  {
    try
    {
      new Test().test();
    }
    catch(Exception e)
    {
      //Just wrap in ServletException
      throw new ServletException("Error invoking endpoint", e);
    }
  }
}
