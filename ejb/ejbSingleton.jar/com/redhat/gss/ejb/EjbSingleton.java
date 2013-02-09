/*
 * To the extent possible under law, Red Hat, Inc. has dedicated all copyright 
 * to this software to the public domain worldwide, pursuant to the CC0 Public 
 * Domain Dedication. This software is distributed without any warranty.  See 
 * <http://creativecommons.org/publicdomain/zero/1.0/>.
 */
package com.redhat.gss.ejb;

import org.jboss.modules.ModuleClassLoader;
import org.jboss.modules.Module;
import org.jboss.logging.Logger;

@javax.ejb.Singleton
@javax.ejb.Startup
public class EjbSingleton
{
  private Logger log = Logger.getLogger(this.getClass().getName());

  @javax.annotation.PostConstruct
  public void test() throws Exception
  {
    log.info("Hello, world!");
  }
}
