package com.redhat.gss.logging;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import javax.annotation.PostConstruct;

@javax.jws.WebService
// @org.apache.cxf.annotations.Logging(pretty=true)
public class LoggingTest
{
  private static Logger log = null; 
  private static Logger thirdParty = null; 

  static
  {
    System.out.println("INIT CALLED");
    // PropertyConfigurator.configure(LoggingTest.class.getResource("/log4j.properties"));
    thirdParty = Logger.getLogger("some.other.LoggingClass");
  }

  public void testLogging()
  {
    log = Logger.getLogger(LoggingTest.class);
    log.info("System.out: " + System.out);
    log.info("Class:   " + log.getClass().getName());
    log.info("Title:   " + log.getClass().getPackage().getImplementationTitle());
    log.info("Vendor:  " + log.getClass().getPackage().getImplementationVendor());
    log.info("Version: " + log.getClass().getPackage().getImplementationVersion());
    thirdParty.error("ERROR");
    log = null;
    System.out.println("System.out goes here");
  }
}
