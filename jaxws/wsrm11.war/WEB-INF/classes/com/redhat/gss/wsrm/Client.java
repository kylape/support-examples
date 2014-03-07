package com.redhat.gss.wsrm;

import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.jws.WebService;

@WebService
public class Client
{
  public String invokeEndpoint() throws Exception
  {
    URL wsdl = new URL("http://localhost:8080/wsrm11/HelloWS?wsdl");
    QName ns = new QName("http://wsrm.gss.redhat.com/", "HelloWSService");
    Service service = Service.create(wsdl, ns);
    Hello port = service.getPort(Hello.class);
    return port.hello("Kyle");
  }

  public static void main(String[] args) throws Exception
  {
    new Client().invokeEndpoint();
  }
}
