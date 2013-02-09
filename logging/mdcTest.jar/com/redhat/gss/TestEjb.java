/*
 * To the extent possible under law, Red Hat, Inc. has dedicated all copyright 
 * to this software to the public domain worldwide, pursuant to the CC0 Public 
 * Domain Dedication. This software is distributed without any warranty.  See 
 * <http://creativecommons.org/publicdomain/zero/1.0/>.
 */
package com.redhat.gss;

import org.jboss.logging.Logger;
import org.jboss.logging.MDC;

@javax.ejb.Singleton
@javax.ejb.Startup
@javax.jws.WebService
public class TestEjb
{
  private Logger log = Logger.getLogger(this.getClass());

  @javax.annotation.PostConstruct
  public void test()
  {
     log.info("Non-MDC message");
     MDC.put("test", "KYLE");
     log.info("With MDC");
     MDC.remove("test");
     log.info("Back to non-MDC");
  }
}
