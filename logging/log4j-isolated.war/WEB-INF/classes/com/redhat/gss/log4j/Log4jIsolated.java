package com.redhat.gss.log4j;

import org.apache.log4j.Logger;

@javax.jws.WebService
public class Log4jIsolated
{
  private Logger log = Logger.getLogger(this.getClass().getName());

  public void logMe()
  {
    log.info("This is a test!");
  }
}
