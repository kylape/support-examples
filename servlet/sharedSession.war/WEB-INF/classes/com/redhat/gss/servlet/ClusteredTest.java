package com.redhat.gss.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jboss.logging.Logger;

public class ClusteredTest extends HttpServlet
{
  private Logger log = Logger.getLogger(this.getClass());

  public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException
  {
    HttpSession session = request.getSession();
    Object o = session.getAttribute("count");
    Integer count = null;
    if(o == null)
    {
      count = new Integer(0);
    }
    else
    {
      count = (Integer)o;
    }

    count = new Integer(count.intValue()+1);

    session.setAttribute("count", count);

    response.setContentType("text/plain");
    PrintWriter writer = null;
    try
    {
      writer = response.getWriter();
      writer.println(count);
    }
    catch(IOException ioe)
    {
      log.error("Failed to write count: " + count);
    }
    finally
    {
      if(writer != null)
        writer.close();
    }
  }
}
