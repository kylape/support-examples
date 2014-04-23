package com.redhat.gss.ws;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import org.jboss.logging.Logger;

@WebServlet("/client")
public class ClientServlet extends HttpServlet
{
  private static Logger log = Logger.getLogger(ClientServlet.class);

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
    throws ServletException, IOException
  {
    try
    {
      boolean useTcclStrategy = false;
      if(req.getParameter("useTcclStrategy") != null)
      {
        useTcclStrategy = true;
      }

      if(req.getParameter("shared") != null)
      {
        log.info("Invoking SHARED endpoint...");
        Client.invokeSharedClient(useTcclStrategy);
      }
      else
      {
        log.info("Invoking endpoint...");
        Client.invokeClient(useTcclStrategy);
      }
    }
    catch(Exception e)
    {
      log.error("Error invoking endpoint", e);
      try
      {
        resp.sendError(500);
      }
      catch(Exception ne)
      {
      }
    }
  }
}
