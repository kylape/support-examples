package com.redhat.gss.sts;

import org.jboss.ws.api.annotation.EndpointConfig;
import javax.jws.HandlerChain;

@org.apache.cxf.annotations.Logging(pretty=true)
@HandlerChain(file="/jaxws-handlers-server.xml")
@javax.jws.WebService
public class TestEndpointImpl
{
  public String hello(String name)
  {
    return "Hello, " + name + "!";
  }
}
