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
    URL wsdl = new URL("http://localhost:8080/helloWorldEjb/HelloWS/HelloWSImpl?wsdl");
    QName qname = new QName("http://jaxws.gss.redhat.com/", "HelloWS");
    final Service service = Service.create(wsdl, qname);

    Runnable r = new Runnable() {
      public void run()
      {
        HelloWS port = service.getPort(HelloWS.class);
        for(int i=0; i<10; i++)
        {
          System.out.println(Thread.currentThread().getName() + ": " + port.hello("Kyle"));
        }
      }
    };

    for(int i=0; i<4; i++)
    {
      new Thread(r).start();
    }
  }
}
