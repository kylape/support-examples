package com.redhat.gss.sts;

import org.jboss.ws.annotation.EndpointConfig;

@javax.jws.WebService
@EndpointConfig(configName="SAML",configFile="META-INF/jaxws-endpoint-config.xml")
public class TestEndpointImpl
{
  public String hello(String name)
  {
    return "Hello, " + name + "!";
  }
}
