package com.redhat.gss.jaxws;

import java.net.URL;
import javax.xml.ws.Service;
import javax.xml.namespace.QName;
import java.util.Enumeration;
import javax.xml.ws.BindingProvider;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Collections;
import org.jboss.logging.Logger;

@javax.jws.WebService
public class ClientEndpoint
{
  private static Logger log = Logger.getLogger(ClientEndpoint.class);

  public String invokeHelloWorld(String name)
  {
    try
    {
      QName serviceName = new QName("http://jaxws.gss.redhat.com/", "HelloWS");
      URL wsdl = new URL("http://localhost:8080/cxfTransform/hello?wsdl");
      Service service = Service.create(wsdl, serviceName);
      HelloWS port = service.getPort(HelloWS.class);
      return port.hello("Kyle");
    }
    catch(Exception e)
    {
      e.printStackTrace();
      return "Fail";
    }
  }
}
