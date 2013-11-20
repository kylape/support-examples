package com.redhat.gss.jaxws;

import java.net.URL;
import javax.xml.ws.Service;
import javax.xml.namespace.QName;
import org.jboss.logging.Logger;
import javax.xml.ws.BindingProvider;

@javax.jws.WebService
public class ClientEndpoint
{
  private static Logger log = Logger.getLogger(ClientEndpoint.class);

  public String invokeHelloWorld(String name)
  {
    try
    {
      QName serviceName = new QName("http://jaxws.gss.redhat.com/", "HelloWS");
      URL wsdl = new URL("http://localhost:8080/disableChunking/hello?wsdl");
      Service service = Service.create(wsdl, serviceName);
      HelloWS port = service.getPort(HelloWS.class);
      ((BindingProvider)port).getRequestContext().put(
        BindingProvider.ENDPOINT_ADDRESS_PROPERTY, "http://localhost:8081/disableChunking/hello");
      log.info("Invoking HelloWS...");
      StringBuilder crazyLongName = new StringBuilder();
      for(int i=0; i<500; i++)
      {
        crazyLongName.append("Kyle James Lape");
      }
      return port.hello(crazyLongName.toString()).toString();
    }
    catch(Exception e)
    {
      e.printStackTrace();
      return "Fail";
    }
  }
}
