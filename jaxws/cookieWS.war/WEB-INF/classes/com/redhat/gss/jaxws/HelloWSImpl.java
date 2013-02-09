/*
 * To the extent possible under law, Red Hat, Inc. has dedicated all copyright 
 * to this software to the public domain worldwide, pursuant to the CC0 Public 
 * Domain Dedication. This software is distributed without any warranty.  See 
 * <http://creativecommons.org/publicdomain/zero/1.0/>.
 */

package com.redhat.gss.jaxws;

import java.util.List;
import java.util.Enumeration;

import javax.xml.ws.spi.Provider;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.annotation.Resource;
import org.jboss.logging.Logger;

@javax.jws.WebService(serviceName="HelloWS", portName="hello", endpointInterface="com.redhat.gss.jaxws.HelloWS")
public class HelloWSImpl implements HelloWS
{
  private Logger log = Logger.getLogger(this.getClass().getName());

  private static final String COOKIE = "name=test123";

  @Resource
  private WebServiceContext wsContext;

  public String hello(String name)
  {
    try
    {
      MessageContext mc = wsContext.getMessageContext();
      HttpSession session = ((HttpServletRequest)mc.get(MessageContext.SERVLET_REQUEST)).getSession();

      // Get a session property "counter" from context
      if (session == null)
        throw new RuntimeException("No session in WebServiceContext");

      Integer counter = (Integer)session.getAttribute(name);
      if (counter == null) 
      {
        counter = new Integer(0);
        log.info("Starting the Session");
      }

      counter = new Integer(counter.intValue() + 1);
      session.setAttribute(name, counter);

      return "Hello, " + name + ". You've invoked this service " + counter + " times.";
    }
    catch(Exception e)
    {
      return "Fail";
    }
  }

  public String getCookie()
  {
    return COOKIE;
  }

  public void testCookie()
  {
    MessageContext mc = wsContext.getMessageContext();
    Enumeration cookies = ((HttpServletRequest)mc.get(MessageContext.SERVLET_REQUEST)).getHeaders("Cookie");
    if(cookies == null)
    {
      throw new RuntimeException("No cookie was set!");
    }

    while(cookies.hasMoreElements())
    {
      String cookie = (String)cookies.nextElement();
      if(COOKIE.equals(cookie))
      {
        return;
      }
    }

    throw new RuntimeException("The correct cookie wasn't found");
  }
}
