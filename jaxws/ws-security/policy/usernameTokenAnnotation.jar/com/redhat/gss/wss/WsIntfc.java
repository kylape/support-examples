package com.redhat.gss.wss;

import javax.jws.WebMethod;

@javax.jws.WebService(
  targetNamespace="http://wss.gss.redhat.com/"
)
public interface WsIntfc
{

  @WebMethod
  public String sayHello(String name);

  @WebMethod
  public String sayGoodbye(String name);
}
