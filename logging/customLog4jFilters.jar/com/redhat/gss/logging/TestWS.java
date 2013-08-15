package com.redhat.gss.logging;

import java.lang.StackTraceElement;
import org.jboss.logging.Logger;

@javax.jws.WebService
@javax.ejb.Stateless
public class TestWS
{
  private Logger log = Logger.getLogger(this.getClass());

  public void logMe()
  {
    log.info("This should NOT be logged");
    log.info("The keyword is gss");
    log.info("The keyword is\ngss");
    
    //Testing to see if the stack trace is considered when filtering
    Exception e = new Exception("The keyword is in the package name below...");
    log.info("Look at the stack trace",e);

    //Testing to see if exception messages are considered when filtering
    e = new Exception("The keyword is gss");
    log.info("Look at the exception message", e);
  }
}
