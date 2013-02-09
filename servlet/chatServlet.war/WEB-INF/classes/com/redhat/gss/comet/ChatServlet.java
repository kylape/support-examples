package com.redhat.gss.comet;

import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.InputStream;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServlet;
import javax.servlet.ServletException;

import org.jboss.logging.Logger;
import org.jboss.servlet.http.HttpEventServlet;
import org.jboss.servlet.http.HttpEvent;

public class ChatServlet extends HttpServlet implements HttpEventServlet 
{
  protected ArrayList<HttpServletResponse> connections = new ArrayList<HttpServletResponse>();
  protected MessageSender messageSender = null;

  private Logger log = Logger.getLogger(this.getClass());
  
  public void init() throws ServletException 
  {
    messageSender = new MessageSender();
    Thread messageSenderThread = 
      new Thread(messageSender, "MessageSender[" + getServletContext().getContextPath() + "]");
    messageSenderThread.setDaemon(true);
    messageSenderThread.start();
  }

  public void destroy() 
  {
    connections.clear();
    messageSender.stop();
    messageSender = null;
  }

  public void event(HttpEvent event) throws IOException, ServletException 
  {
    event.setTimeout(30000);
    HttpServletRequest request = event.getHttpServletRequest();
    HttpServletResponse response = event.getHttpServletResponse();
    if (event.getType() == HttpEvent.EventType.BEGIN) 
    {
      log.info("Stack trace", new Exception("stack trace"));
      log.info("Begin for session: " + request.getSession(true).getId());
      PrintWriter writer = response.getWriter();
      writer.println("<!doctype html public \"-//w3c//dtd html 4.0 transitional//en\">");
      writer.println("<head><title>JSP Chat</title></head><body bgcolor=\"#FFFFFF\">");
      writer.flush();
      synchronized(connections) 
      {
        connections.add(response);
      }
    } 
    else if (event.getType() == HttpEvent.EventType.ERROR) 
    {
      log.info("Error for session: " + request.getSession(true).getId());
      synchronized(connections) 
      {
        connections.remove(response);
      }
      event.close();
    } 
    else if (event.getType() == HttpEvent.EventType.END ||
             event.getType() == HttpEvent.EventType.TIMEOUT) 
    {
      log.info("End for session: " + request.getSession(true).getId());
      synchronized(connections) 
      {
        connections.remove(response);
      }
      PrintWriter writer = response.getWriter();
      writer.println("</body></html>");
      event.close();
    } 
    else if (event.getType() == HttpEvent.EventType.READ) 
    {
      InputStream is = request.getInputStream();
      byte[] buf = new byte[512];
      do 
      {
        int n = is.read(buf); //can throw an IOException
        if (n > 0) 
        {
          String s = new String(buf, 0, n) ;
          String session = request.getSession(true).getId();
          log.info("Read " + n + " bytes: " + s
              + " for session: " + session);
          messageSender.send(session, s);
        } else if (n < 0) {
          log.error("Didn't read anything from the stream");
          return;
        }
      } while (is.available() > 0);
    }
    else
    {
      log.info("Other event: " + event.getType());
    }
  }

  public class MessageSender implements Runnable 
  {
    protected boolean running = true;
    protected ArrayList<String> messages = new ArrayList<String>();
    
    public MessageSender() 
    { }
    
    public void stop() 
    {
      running = false;
    }

    /**
     * Add message for sending.
     */
    public void send(String user, String message) 
    {
      synchronized (messages) 
      {
        messages.add("[" + user + "]: " + message);
        messages.notify();
      }
    }

    public void run() 
    {
      while (running) 
      {
        if (messages.size() == 0) 
        {
          try 
          {
            synchronized (messages) 
            {
              messages.wait();
            }
          } catch (InterruptedException e) 
          {
            // Ignore
          }
        }

        synchronized (connections) 
        {
          String[] pendingMessages = null;

          synchronized (messages) 
          {
            pendingMessages = messages.toArray(new String[0]);
            messages.clear();
          }

          // Send any pending message on all the open connections
          for (int i = 0; i < connections.size(); i++) 
          {
            try 
            {
              PrintWriter writer = connections.get(i).getWriter();
              for (int j = 0; j < pendingMessages.length; j++) 
              {
                writer.println(pendingMessages[j] + "<br>");
              }
              writer.flush();
            } 
            catch (IOException e) 
            {
              log.info("IOExeption sending message", e);
            }
          }
        }
      }
    }
  }
}
