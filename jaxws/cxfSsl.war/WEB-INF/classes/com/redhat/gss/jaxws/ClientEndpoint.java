package com.redhat.gss.jaxws;

import java.net.URL;
import javax.xml.ws.Service;
import javax.xml.namespace.QName;

@javax.jws.WebService
public class ClientEndpoint
{
  public String invokeHelloWorld(String name) throws Exception
  {
    new TestClient().invoke();
    return "success";
  }
}
