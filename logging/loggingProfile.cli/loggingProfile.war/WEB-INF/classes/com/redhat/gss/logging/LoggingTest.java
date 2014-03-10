package com.redhat.gss.logging;

import org.jboss.logging.Logger;

@javax.jws.WebService
@org.apache.cxf.annotations.Logging(pretty=true)
public class LoggingTest
{
  private static Logger log = Logger.getLogger(LoggingTest.class);
  private static Logger thirdParty = Logger.getLogger("some.other.LoggingClass");

  public void testLogging()
  {
    log.info("Hello, world!");
    thirdParty.info("Hello, world!");
  }
}
