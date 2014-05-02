package com.jboss.examples.ws.usernameToken;

import java.security.Principal;
import javax.security.auth.Subject;
import java.util.Set;

import javax.annotation.Resource;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;
import javax.xml.ws.WebServiceContext;
import org.jboss.ws.annotation.EndpointConfig;

import javax.security.jacc.PolicyContext;  
import javax.security.jacc.PolicyContextException; 

import javax.ejb.Stateless;
import javax.xml.ws.BindingType;

@Stateless
@javax.jws.WebService
@org.jboss.ejb3.annotation.SecurityDomain("JBossWS")
@javax.annotation.security.RolesAllowed("friend")
@BindingType(javax.xml.ws.soap.SOAPBinding.SOAP12HTTP_BINDING)
@EndpointConfig(configFile="META-INF/jaxws-endpoint-config.xml", configName = "Standard WSSecurity Endpoint")
public class UsernameToken implements UsernameTokenWS
{
  private static final String SUBJECT_CONTEXT_KEY = "javax.security.auth.Subject.container"; 

	public String hello (String name) throws Exception
	{
    Subject caller = (Subject) PolicyContext.getContext(SUBJECT_CONTEXT_KEY);  

    Principal p = null;
    for(Principal pr : caller.getPrincipals())
    {
      p = pr;
      break;
    }
    System.out.println("Logged in principal: " + p.getName());
		System.out.println("UsernameToken: hello: " + name);
		return "Hello " + name;
	}
}
