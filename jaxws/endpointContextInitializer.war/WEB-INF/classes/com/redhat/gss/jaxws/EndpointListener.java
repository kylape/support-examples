/*
 * To the extent possible under law, Red Hat, Inc. has dedicated all copyright 
 * to this software to the public domain worldwide, pursuant to the CC0 Public 
 * Domain Dedication. This software is distributed without any warranty.  See 
 * <http://creativecommons.org/publicdomain/zero/1.0/>.
 */
package com.redhat.gss.jaxws;

import javax.servlet.ServletContextListener;
import javax.servlet.ServletContextEvent;

import org.jboss.logging.Logger;
import javax.xml.ws.Endpoint;

public class EndpointListener implements ServletContextListener
{
  private Logger log = Logger.getLogger(this.getClass());
  private Endpoint endpoint;

  public void contextInitialized(ServletContextEvent event)
  {
    log.info("Initializing endpoint...");
    try
    {
      HelloWSImpl ws = new HelloWSImpl();
      endpoint = Endpoint.create(ws);
      endpoint.publish("http://localhost:8080/endpointContextListener/hello");
    }
    catch(Exception e)
    {
      log.error("Could not publish endpoint", e);
    }
  }

  public void contextDestroyed(ServletContextEvent event)
  {
    endpoint.stop();
  }
}
