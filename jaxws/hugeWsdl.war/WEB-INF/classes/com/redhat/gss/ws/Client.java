package com.redhat.gss.ws;

import java.net.URL;
import javax.xml.ws.Service;
import javax.xml.namespace.QName;
import org.jboss.wsf.stack.cxf.client.UseTCCLBusFeature;

import org.jboss.logging.Logger;

public class Client
{
  private static Service sharedService = null;

  public static void invokeSharedClient(boolean useTcclStrategy) throws Exception
  {
    BigHelloInterface port = null;
    synchronized(Client.class)
    {
      if(sharedService == null)
      {
        sharedService = createService(useTcclStrategy);
      }
      port = sharedService.getPort(BigHelloInterface.class);
    }
    invoke(port);
  }

  public static synchronized void invokeClient(boolean useTcclStrategy) throws Exception
  {
    BigHelloInterface port = null;
    synchronized(Client.class)
    {
      Service service = createService(useTcclStrategy);
      port = service.getPort(BigHelloInterface.class);
    }
    invoke(port);
  }

  private static void invoke(BigHelloInterface port) throws Exception
  {
    com.redhat.gss.ws23.MassiveCollection mc23 = new com.redhat.gss.ws23.MassiveCollection(); 
    com.redhat.gss.ws23.BigObject14 bigObject14 = new com.redhat.gss.ws23.BigObject14();
    bigObject14.setArg8("Something silly");
    port.hello(
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      mc23,
      null,
      null,
      null,
      null,
      null,
      null,
      null
    );
  }

  private static Service createService(boolean useTcclStrategy) throws Exception
  {
    QName serviceName = new QName("http://ws.gss.redhat.com/", "BigHelloService");
    URL wsdl = new URL("http://localhost:8080/hugeWsdl/BigHello?wsdl");
    if(useTcclStrategy)
    {
      return Service.create(wsdl, serviceName, new UseTCCLBusFeature());
    }
    else
    {
      return Service.create(wsdl, serviceName);
    }
  }
}
