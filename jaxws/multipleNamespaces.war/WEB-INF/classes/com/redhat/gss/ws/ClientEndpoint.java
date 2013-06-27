/*
 * To the extent possible under law, Red Hat, Inc. has dedicated all copyright 
 * to this software to the public domain worldwide, pursuant to the CC0 Public 
 * Domain Dedication. This software is distributed without any warranty.  See 
 * <http://creativecommons.org/publicdomain/zero/1.0/>.
 */
package com.redhat.gss.ws;

import javax.xml.ws.Service;
import java.net.URL;
import javax.xml.namespace.QName;
import java.net.MalformedURLException;

@javax.jws.WebService
public class ClientEndpoint
{
  private static Service service = null;

  public String invokeHelloService()
  {
    Cat cat = new Cat();
    cat.setName("Mittens º");
    cat.setType("Tabby");
    return getPort().hello(cat);
  }

  private static synchronized HelloWSInterface getPort()
  {
    if(service == null)
    {
      try
      {
        URL wsdl = new URL("http://localhost:8080/multipleNamespaces/hello?wsdl");
        QName qname = new QName("http://ws.gss.redhat.com/", "HelloWSService");
        service = Service.create(wsdl, qname);
      }
      catch(MalformedURLException e)
      {
        //I dunno..
      }
    }
    return (HelloWSInterface)service.getPort(HelloWSInterface.class);
  }
}
