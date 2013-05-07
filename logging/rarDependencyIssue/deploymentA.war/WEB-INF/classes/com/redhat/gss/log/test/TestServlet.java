package com.redhat.gss.log.test;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

public class TestServlet extends HttpServlet
{
  private Logger log = Logger.getLogger(this.getClass());

  public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException
  {
    String name = request.getParameter("name");

    response.setContentType("text/plain");
    PrintWriter writer = null;
    try
    {
      writer = response.getWriter();

      if(name == null || name.equals(""))
      {
        log.warn("Couldn't find name parameter");
        writer.println("Please set the \"name\" parameter");
      }
      else
      {
        log.warn("Name: " + name);
        TestVO vo = new TestVO();
        vo.setName(name);
        writer.println(vo.sayHello());
      }
    }
    catch(IOException ioe)
    {
      log.error("Failed to write response.", ioe);
    }
    finally
    {
      if(writer != null)
        writer.close();
    }
  }
}
