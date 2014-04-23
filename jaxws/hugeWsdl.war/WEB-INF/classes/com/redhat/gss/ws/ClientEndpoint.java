package com.redhat.gss.ws;

import org.jboss.logging.Logger;

@javax.jws.WebService
public class ClientEndpoint
{
  private static Logger log = Logger.getLogger(ClientEndpoint.class);

  public void invokeBigHello() throws Exception
  {
    Client.invokeClient(false);
  }
}
