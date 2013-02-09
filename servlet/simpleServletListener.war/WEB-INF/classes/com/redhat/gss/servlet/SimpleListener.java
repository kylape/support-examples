/*
 * To the extent possible under law, Red Hat, Inc. has dedicated all copyright 
 * to this software to the public domain worldwide, pursuant to the CC0 Public 
 * Domain Dedication. This software is distributed without any warranty.  See 
 * <http://creativecommons.org/publicdomain/zero/1.0/>.
 */
package com.redhat.gss.servlet;

import javax.servlet.ServletContextListener;
import javax.servlet.ServletContextEvent;

import org.jboss.logging.Logger;

public class SimpleListener implements ServletContextListener
{
  private Logger log = Logger.getLogger(this.getClass().getName());

  public void contextInitialized(ServletContextEvent event)
  {
    log.info("Context initialized!");
  }

  public void contextDestroyed(ServletContextEvent event)
  {
  }
}
