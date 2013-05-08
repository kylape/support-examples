package com.redhat.gss.jaxws;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.net.URL;

import javax.xml.ws.BindingProvider;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.Service;
import javax.xml.namespace.QName;

import org.jboss.ws.core.StubExt;

public class TestClient
{
  public static void main(String[] args) throws Exception
  {
    final URL wsdl = new URL("http://localhost:8080/webServiceRefTest/ClientEndpointImpl?wsdl");
    final QName qname = new QName("http://jaxws.gss.redhat.com/", "ClientEndpointImplService");
    final Service service = Service.create(wsdl, qname);

    Runnable r = new Runnable() {
      final ClientEndpoint port = service.getPort(ClientEndpoint.class);
      public void run()
      {
        for(int i=0; i<500; i++)
          port.invokeHelloWorld("Kyle");
          System.out.print(".");
      }
    };

    for(int i=0; i<100; i++)
      new Thread(r).start();
  }
}
