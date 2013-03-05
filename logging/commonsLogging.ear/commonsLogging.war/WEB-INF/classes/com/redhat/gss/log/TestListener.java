package com.redhat.gss.log;

import java.io.IOException;
import java.net.URL;

import javax.servlet.ServletContextListener;
import javax.servlet.ServletContextEvent;

import org.apache.log4j.PropertyConfigurator;

public class TestListener implements ServletContextListener
{
  public void contextInitialized(ServletContextEvent event)
  {
    System.out.println("Initializing log4j...");
    URL log4jprops = Thread.currentThread().getContextClassLoader().getResource("/log4j.properties");

    if(log4jprops == null)
      throw new IllegalStateException("log4j.properties not found");

    PropertyConfigurator.configure(log4jprops);
  }

  public void contextDestroyed(ServletContextEvent event)
  {
  }
}
