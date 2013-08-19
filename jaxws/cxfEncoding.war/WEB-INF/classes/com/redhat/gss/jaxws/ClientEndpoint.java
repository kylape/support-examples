package com.redhat.gss.jaxws;

import java.net.URL;
import javax.xml.ws.Service;
import javax.xml.namespace.QName;
import org.apache.cxf.message.Message;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.frontend.ClientProxy;

@javax.jws.WebService
public class ClientEndpoint
{
  public String invokeHelloWorld(String name)
  {
    try
    {
      QName serviceName = new QName("http://jaxws.gss.redhat.com/", "HelloWS");
      URL wsdl = new URL("http://localhost:8080/cxfEncoding/hello?wsdl");
      Service service = Service.create(wsdl, serviceName);
      HelloWS port = service.getPort(serviceName, HelloWS.class);
      Client proxy = ClientProxy.getClient(port);
      proxy.getOutInterceptors().add(new org.apache.cxf.interceptor.LoggingOutInterceptor());
      proxy.getRequestContext().put(Message.ENCODING, "ISO-8859-1");
      return port.hello("Kylé");
    }
    catch(Exception e)
    {
      e.printStackTrace();
      return "Fail";
    }
  }
}
