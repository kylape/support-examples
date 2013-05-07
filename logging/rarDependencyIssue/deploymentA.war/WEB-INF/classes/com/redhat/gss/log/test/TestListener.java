package com.redhat.gss.log.test;

import java.io.IOException;
import java.net.URL;

import javax.servlet.ServletContextListener;
import javax.servlet.ServletContextEvent;

import org.apache.log4j.xml.DOMConfigurator;

public class TestListener implements ServletContextListener
{
  public void contextInitialized(ServletContextEvent event)
  {
    URL log4jXml = Thread.currentThread().getContextClassLoader().getResource("/my-log4j.xml");

    if(log4jXml == null)
      throw new IllegalStateException("my-log4j.xml not found");

    DOMConfigurator.configure(log4jXml);
  }

  public void contextDestroyed(ServletContextEvent event)
  {
  }
}
