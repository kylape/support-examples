package com.redhat.gss.ejb;

import java.security.Principal;

@javax.ejb.Stateless
@javax.jws.WebService
@org.jboss.ws.api.annotation.WebContext(authMethod="BASIC")
@org.jboss.security.annotation.SecurityDomain("other")
@org.apache.cxf.annotations.Logging(pretty=true)
@javax.annotation.security.PermitAll
public class CallerAuthEjb
{
  @javax.annotation.Resource
  javax.ejb.EJBContext context;

  public String getCaller()
  {
    Principal p = context.getCallerPrincipal();
    return p.getName();
  }
}
