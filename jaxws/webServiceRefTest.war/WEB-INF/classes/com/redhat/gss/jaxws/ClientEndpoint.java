package com.redhat.gss.jaxws;

@javax.jws.WebService
public interface ClientEndpoint
{
  public String invokeHelloWorld(String name);
}
