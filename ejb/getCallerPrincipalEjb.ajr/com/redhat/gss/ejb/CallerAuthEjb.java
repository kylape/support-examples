package com.redhat.gss.ejb;

import java.security.Principal;

import javax.security.auth.Subject;

import org.jboss.security.SecurityContext;
import org.jboss.security.SecurityContextAssociation;
import org.jboss.security.SimplePrincipal;
import org.jboss.security.plugins.JBossSecurityContextUtil;

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
    /*
    Principal p = context.getCallerPrincipal();
    return p.getName();
    */

    SecurityContext securityContext = SecurityContextAssociation.getSecurityContext();
    SimplePrincipal sp = (SimplePrincipal) new JBossSecurityContextUtil(securityContext).getUserPrincipal();
    Subject s = new JBossSecurityContextUtil(securityContext).getSubject();
    
    StringBuilder b = new StringBuilder();

    b.append(sp.getName() + ", ");
    for(Principal p : s.getPrincipals())
    {
      b.append(p.getName() + ", ");
    }
    b.setLength(b.length() - 2);

    return b.toString();

  }
}
