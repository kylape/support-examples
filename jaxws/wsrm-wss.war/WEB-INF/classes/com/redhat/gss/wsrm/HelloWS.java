package com.redhat.gss.wsrm;

import org.jboss.ws.api.annotation.EndpointConfig;

import javax.jws.WebService;
import org.apache.cxf.annotations.Logging;
import org.jboss.logging.Logger;

@WebService(endpointInterface="com.redhat.gss.wsrm.Hello", wsdlLocation="WEB-INF/wsdl/the.wsdl")
@EndpointConfig(configFile = "WEB-INF/jaxws-endpoint-config.xml", configName = "Custom WS-Security Endpoint")
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
