/*
 * To the extent possible under law, Red Hat, Inc. has dedicated all copyright
 * to this software to the public domain worldwide, pursuant to the CC0 Public
 * Domain Dedication. This software is distributed without any warranty.  
 * See <http://creativecommons.org/publicdomain/zero/1.0/>.
 */
package com.jboss.examples.ws;

//@javax.jws.WebService(serviceName="HelloWorld",wsdlLocation="WEB-INF/wsdl/HelloWorld.wsdl")
@javax.jws.WebService(serviceName="HelloWorld",wsdlLocation="http://ws.examples.jboss.com/helloWorld.wsdl")
public class HelloWorldImpl implements HelloWorldPortType
{
  @javax.jws.WebMethod
  public HelloReturn hello(HelloInput input)
  {
    HelloReturn ret = new HelloReturn();

    ret.setExcited(input.isExcited());

    ret.setGreeting("Hello, " + input.getName());

    return ret;
  }
}
