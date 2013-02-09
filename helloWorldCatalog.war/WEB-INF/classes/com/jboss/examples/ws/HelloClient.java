/*
 * To the extent possible under law, Red Hat, Inc. has dedicated all copyright
 * to this software to the public domain worldwide, pursuant to the CC0 Public
 * Domain Dedication. This software is distributed without any warranty.  
 * See <http://creativecommons.org/publicdomain/zero/1.0/>.
 */
package com.jboss.examples.ws;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.BindingProvider;
import java.net.URL;

public class HelloClient
{
  HelloWorldPortType port = null;

  public static void main(String[] args) throws Exception
  {
     HelloClient client = new HelloClient();
     for(int i=0; i<10; i++)
     {
       HelloReturn ret = client.hello("Kyle");
       System.out.println("excited: " + ret.isExcited());
       System.out.println("Greeting: " + ret.getGreeting());
     }
  }

  public HelloClient() throws Exception
  {
     QName qname = new QName("http://ws.examples.jboss.com/", "HelloWorldImplService");
     URL wsdl = new URL("http://localhost:8080/helloWorld/Simple?wsdl");

     Service service = Service.create(wsdl, qname);
     port = service.getPort(HelloWorldPortType.class);
     
     ((BindingProvider)port).getRequestContext()
       .put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY,"http://localhost:8080/helloWorld/Simple");
  }

  public HelloReturn hello(String name)
  {
     HelloInput input = new HelloInput();
     input.setExcited(true);
     input.setName(name);

     return port.hello(input);
  }
}
