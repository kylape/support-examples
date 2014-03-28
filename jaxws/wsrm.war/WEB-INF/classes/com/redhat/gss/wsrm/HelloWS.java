package com.redhat.gss.wsrm;

import javax.jws.WebService;

import org.apache.cxf.annotations.Logging;
import org.apache.cxf.annotations.Policy;
import org.jboss.logging.Logger;

@WebService(endpointInterface="com.redhat.gss.wsrm.Hello", wsdlLocation="WEB-INF/wsdl/the.wsdl")
@Logging(pretty=true)
public class HelloWS
{
  private static Logger log = Logger.getLogger(HelloWS.class);

  public String hello(String name)
  {
    String greeting = "Hello, " + name + "!";
    log.info(greeting);
    return greeting;
  }
}
