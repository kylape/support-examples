/*
 * To the extent possible under law, Red Hat, Inc. has dedicated all copyright 
 * to this software to the public domain worldwide, pursuant to the CC0 Public 
 * Domain Dedication. This software is distributed without any warranty.  See 
 * <http://creativecommons.org/publicdomain/zero/1.0/>.
 */
package com.redhat.gss.ws;

import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;

public class TestClient
{
  private static final String NS = "http://ws.gss.redhat.com/";
  public static void main(String[] args) throws Exception
  {
    URL wsdl = new URL("http://localhost:8080/wsException/TestWs?wsdl");
    QName serviceNs = new QName(NS,"TestWsService");
    Service service = Service.create(wsdl, serviceNs);
    WsIntfc port = service.getPort(WsIntfc.class);
    try
    {
      port.test();
    }
    catch(MyException me)
    {
      System.out.println("MyException: " + me.getMessage() + " : " + me.getId() + " : " + me.getFrom() + " : " + me.getSummary());
      System.out.flush();
      Thread.sleep(10);
      me.printStackTrace();
    }
  }
}
