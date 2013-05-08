package com.redhat.gss.jaxws;

import java.net.URL;
import javax.xml.ws.Service;
import javax.xml.namespace.QName;
import javax.xml.ws.WebServiceRef;

@javax.ejb.Stateless
@javax.jws.WebService(endpointInterface="com.redhat.gss.jaxws.ClientEndpoint")
public class ClientEndpointImpl
{
  @WebServiceRef(type=HelloWSService.class, wsdlLocation="META-INF/hello.wsdl")
  HelloWS port;

  public String invokeHelloWorld(String name)
  {
    try
    {
      // QName serviceName = new QName("http://jaxws.gss.redhat.com/", "HelloWS");
      // URL wsdl = new URL("http://localhost:8080/helloWorld/hello?wsdl");
      // Service service = Service.create(wsdl, serviceName);
      // HelloWS port = service.getPort(HelloWS.class);
      return port.hello("Kyle");
    }
    catch(Exception e)
    {
      e.printStackTrace();
      return "Fail";
    }
  }
}

