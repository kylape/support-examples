/*
 * To the extent possible under law, Red Hat, Inc. has dedicated all copyright 
 * to this software to the public domain worldwide, pursuant to the CC0 Public 
 * Domain Dedication. This software is distributed without any warranty.  See 
 * <http://creativecommons.org/publicdomain/zero/1.0/>.
 */

package com.redhat.gss.jaxws;

import javax.xml.ws.spi.Provider;
import org.jboss.logging.Logger;

@javax.ejb.Stateless
@javax.jws.WebService(serviceName="HelloWS",endpointInterface="com.redhat.gss.jaxws.HelloWS")
public class HelloWSImpl implements HelloWS
{
  private Logger log = Logger.getLogger(this.getClass().getName());

  public HelloWSImpl()
  {
    log.info("Creating new instance");
  }

  public String hello(String name)
  {
    try
    {
      return "Hello, " + name;
    }
    catch(Exception e)
    {
      return "Fail";
    }
  }
}
