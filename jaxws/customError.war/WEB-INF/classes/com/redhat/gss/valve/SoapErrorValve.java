package com.redhat.gss.valve;

import java.io.IOException;

import org.apache.catalina.deploy.LoginConfig;
import org.apache.catalina.valves.ErrorReportValve;
import org.apache.catalina.connector.Request;
import org.apache.catalina.connector.Response;
import javax.servlet.http.HttpServletResponse;
import org.jboss.logging.Logger;

public class SoapErrorValve extends ErrorReportValve
{
  private static Logger log = Logger.getLogger(SoapErrorValve.class);

  @Override
  public void report(Request request, Response response, Throwable t)
  {
    log.info("SUCK IT TREBECK", new Exception());
    super.report(request, response, t);
  }
}
