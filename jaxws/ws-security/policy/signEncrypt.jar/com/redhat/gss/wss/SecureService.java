package com.redhat.gss.wss;

import javax.annotation.security.RolesAllowed;
import org.apache.cxf.interceptor.InInterceptors;
import org.jboss.ws.api.annotation.EndpointConfig;
import org.jboss.logging.Logger;
import org.jboss.ejb3.annotation.SecurityDomain;

@javax.ejb.Stateless
@javax.jws.WebService(wsdlLocation="META-INF/wsdl/secureService.wsdl")
//@SecurityDomain("other")
@EndpointConfig(configFile = "META-INF/jaxws-endpoint-config.xml", configName = "Custom WS-Security Endpoint")
public class SecureService
{

  public String sayHello(String name)
  {
    return "Hello, " + name;
  }

  public String sayGoodbye(String name)
  {
    return "Goodbye, " + name;
  }
}
