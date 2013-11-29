package com.redhat.gss.cdi;

import org.jboss.logging.Logger;

@AuditMethods
@javax.ejb.Stateless
@javax.jws.WebService
public class TestEjb
{
  private static Logger log = Logger.getLogger(TestEjb.class);

  @javax.jws.WebMethod(action="hello")
  public void hello()
  {
    log.debug("Hello, world!");
  }
}
