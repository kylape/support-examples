package com.redhat.gss.wss;

import javax.annotation.security.RolesAllowed;
import org.apache.cxf.interceptor.InInterceptors;
import org.jboss.ws.api.annotation.EndpointConfig;
import org.jboss.logging.Logger;
import org.jboss.ejb3.annotation.SecurityDomain;
import org.apache.cxf.annotations.Policy;
import org.apache.cxf.annotations.Policies;

@javax.ejb.Stateless
// @javax.jws.WebService(wsdlLocation="META-INF/wsdl/secureService.wsdl")
@javax.jws.WebService
@Policies({
  @Policy(uri="META-INF/wsdl/utPolicy.xml")
})
@SecurityDomain("other")
@EndpointConfig(configFile = "META-INF/jaxws-endpoint-config.xml", configName = "Custom WS-Security Endpoint")
@InInterceptors(interceptors = {
  "org.jboss.wsf.stack.cxf.security.authentication.SubjectCreatingPolicyInterceptor"
  }
)
public class SecureService
{

  @RolesAllowed("hello")
  public String sayHello(String name)
  {
    return "Hello, " + name;
  }

  //@RolesAllowed("goodbye")
  public String sayGoodbye(String name)
  {
    return "Goodbye, " + name;
  }
}
