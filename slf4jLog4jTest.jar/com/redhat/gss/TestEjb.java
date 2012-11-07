/*
 * To the extent possible under law, Red Hat, Inc. has dedicated all copyright 
 * to this software to the public domain worldwide, pursuant to the CC0 Public 
 * Domain Dedication. This software is distributed without any warranty.  See 
 * <http://creativecommons.org/publicdomain/zero/1.0/>.
 */
package com.redhat.gss;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@javax.ejb.Singleton
@javax.ejb.Startup
@javax.jws.WebService
public class TestEjb
{
  private org.apache.log4j.Logger log2 = org.apache.log4j.Logger.getLogger(this.getClass());
  private Logger log = LoggerFactory.getLogger(this.getClass());

  @javax.annotation.PostConstruct
  public void test()
  {
     log2.info("LOG4J");
     log.info("SLF4J");
  }

  public void boom()
  {
    log.info("SLF4J");
  }
}
